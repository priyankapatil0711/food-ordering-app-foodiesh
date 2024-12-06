package com.foodordering.foodiesh.repository

import com.foodordering.foodiesh.api.ApiService
import com.foodordering.foodiesh.model.LoginRequest
import com.foodordering.foodiesh.model.LoginResponse
import com.foodordering.foodiesh.model.Product
import com.foodordering.foodiesh.model.RegisterRequest
import com.foodordering.foodiesh.model.RegisterResponse
import com.foodordering.foodiesh.utility.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(email: String, password: String): Resource<LoginResponse> {
        return try {
            val response = apiService.login(LoginRequest(email, password))

            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Login failed: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Network error: ${e.localizedMessage}")
        }
    }

    suspend fun register(name: String, email: String, password: String,confirmPassword: String): Resource<RegisterResponse> {
        return try {
            val response = apiService.register(RegisterRequest(name, email, password, confirmPassword))

            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Registration failed: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Network error: ${e.localizedMessage}")
        }
    }

    suspend fun getProducts(): List<Product> {
        val response = apiService.getProducts()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Failed to fetch products")
        }
    }
}

