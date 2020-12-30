package com.doggo.molly.doggr.ui.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.doggo.molly.doggr.data.repository.AuthRepository

class LoginViewModel @ViewModelInject constructor(
    authRepository: AuthRepository
): ViewModel() {
}
