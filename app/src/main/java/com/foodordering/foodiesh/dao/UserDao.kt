package com.foodordering.foodiesh.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert()
    fun insertUser(user: UserEntity)

    @Update()
    fun updatetUser(user: UserEntity)

    @Delete()
    fun deleteUser(user: UserEntity)

    //@Query()


}