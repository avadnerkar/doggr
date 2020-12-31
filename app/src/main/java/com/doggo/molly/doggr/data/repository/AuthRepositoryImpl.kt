package com.doggo.molly.doggr.data.repository

import com.doggo.molly.doggr.data.model.ResponseResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl : AuthRepository {

    private val firebaseAuth by lazy { Firebase.auth }

    override suspend fun login(email: String, password: String): ResponseResult<Unit> =
        suspendCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { continuation.resume(ResponseResult.success(Unit)) }
                .addOnFailureListener {
                    it.printStackTrace()
                    continuation.resume(ResponseResult.error(it.message.orEmpty()))
                }
        }

    override suspend fun signup(email: String, password: String): ResponseResult<Unit> =
        suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { continuation.resume(ResponseResult.success(Unit)) }
                .addOnFailureListener {
                    it.printStackTrace()
                    continuation.resume(ResponseResult.error(it.message.orEmpty()))
                }
        }

    override suspend fun logout(): ResponseResult<Unit> {
        firebaseAuth.signOut()
        return ResponseResult.success(Unit)
    }

    override fun observeAuthentication(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            offer(it.currentUser != null)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }
}
