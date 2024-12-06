package com.foodordering.foodiesh.dao

import androidx.room.RoomDatabase


@androidx.room.Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}