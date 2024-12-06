package com.foodordering.foodiesh.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodordering.foodiesh.api.RetrofitClient
import com.foodordering.foodiesh.model.LoginRequest
import com.foodordering.foodiesh.model.LoginResponse
import com.foodordering.foodiesh.model.Product
import com.foodordering.foodiesh.model.RegisterRequest
import com.foodordering.foodiesh.model.RegisterResponse
import com.foodordering.foodiesh.repository.AuthRepository
import com.foodordering.foodiesh.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    private val _loginResult = MutableLiveData<Resource<LoginResponse>>()
    val loginResult: LiveData<Resource<LoginResponse>> = _loginResult

    private val _registrationResult = MutableLiveData<Resource<RegisterResponse>>()
    val registrationResult: LiveData<Resource<RegisterResponse>> = _registrationResult

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> get() = _registerResponse

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun login(email: String, password: String) {
        _loginResult.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val result = authRepository.login(email, password)
                _loginResult.value = result
            } catch (e: Exception) {
                _loginResult.value = Resource.Error("Network error: ${e.localizedMessage}")
            }
        }
    }

    fun register(name: String, email: String, password: String, confirmPassword: String) {
        _registrationResult.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val result = authRepository.register(name, email, password, confirmPassword)
                _registrationResult.value = result
            } catch (e: Exception) {
                _registrationResult.value =
                    Resource.Error("Network error: ${e.localizedMessage}")
            }
        }
    }

    fun getProducts() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = authRepository.getProducts()
                _products.postValue(response)
                _isLoading.postValue(false)
            } catch (e: Exception) {
                _errorMessage.postValue("Failed to fetch products: ${e.message}")
                _isLoading.postValue(false)
            }
        }
    }
}


