package com.foodordering.foodiesh.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foodordering.foodiesh.repository.AuthRepository


class AuthViewModelFactory (private val authRepository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
        @Suppress("UNCHECKED_CAST")
        return AuthViewModel(authRepository) as T        }
    throw IllegalArgumentException("Unknown ViewModel class")    }
}