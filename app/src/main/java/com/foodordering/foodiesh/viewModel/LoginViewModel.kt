package com.foodordering.foodiesh.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodordering.foodiesh.api.ApiService
import com.foodordering.foodiesh.model.LoginRequest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed class LoginState {
    object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _loginState = mutableStateOf<LoginState>(LoginState.Loading)
    val loginState: State<LoginState> = _loginState

    fun login(username: String, password: String) {
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            try {
                val response = apiService.login(LoginRequest(username, password))
                _loginState.value = LoginState.Success(response.message())
            } catch (e: HttpException) {
                _loginState.value = LoginState.Error("Login failed. Please try again.")
            } catch (e: IOException) {
                _loginState.value = LoginState.Error("Network error. Please check your connection.")
            }
        }
    }
}
