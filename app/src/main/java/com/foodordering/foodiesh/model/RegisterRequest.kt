package com.foodordering.foodiesh.model

data class RegisterRequest (
    val name : String,
    val email: String,
    val password: String,
    val confirmPassword: String
)