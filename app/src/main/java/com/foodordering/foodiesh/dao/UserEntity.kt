package com.foodordering.foodiesh.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val token: String
)