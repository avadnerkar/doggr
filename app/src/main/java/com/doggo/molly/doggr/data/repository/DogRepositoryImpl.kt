package com.doggo.molly.doggr.data.repository

import com.doggo.molly.doggr.data.model.Dog
import com.doggo.molly.doggr.data.model.ResponseResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DogRepositoryImpl(
    val authRepository: AuthRepository
) : DogRepository {

    private val firestore by lazy { Firebase.firestore }
    private val auth by lazy { Firebase.auth }
    private var dogRegistration: ListenerRegistration? = null

    override suspend fun addDog(dog: Dog): ResponseResult<Unit> {
        return suspendCoroutine { continuation ->
            auth.currentUser?.uid?.let {
                firestore.collection("users/$it/dogs")
                    .add(dog)
                    .addOnSuccessListener { continuation.resume(ResponseResult.success(Unit)) }
                    .addOnFailureListener { continuation.resume(ResponseResult.error(it.message.orEmpty())) }
            } ?: continuation.resume(ResponseResult.Error("Unauthorized"))
        }
    }

    override fun observeDogs(): Flow<List<Dog>> {
        return authRepository.observeAuthentication().flatMapLatest { authenticated ->
            if (authenticated) {
                dogRegistration?.remove()
                callbackFlow {
                    dogRegistration = firestore.collection("users/${auth.currentUser!!.uid}/dogs")
                        .addSnapshotListener { collection, error ->
                            if (error != null) {
                                cancel(CancellationException(cause = error))
                            } else {
                                offer(collection?.documents?.mapNotNull { document ->
                                    document.toObject<Dog>()
                                }.orEmpty())
                            }
                        }
                    awaitClose {
                        dogRegistration?.remove()
                    }
                }
            } else {
                dogRegistration?.remove()
                flowOf(emptyList())
            }
        }
    }
}
