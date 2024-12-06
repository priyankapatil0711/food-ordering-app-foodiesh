package com.foodordering.foodiesh.api

import com.foodordering.foodiesh.model.LoginRequest
import com.foodordering.foodiesh.model.LoginResponse
import com.foodordering.foodiesh.model.Product
import com.foodordering.foodiesh.model.RegisterRequest
import com.foodordering.foodiesh.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
}
