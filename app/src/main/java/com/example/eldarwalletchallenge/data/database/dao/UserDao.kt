package com.example.eldarwalletchallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eldarwalletchallenge.data.database.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table WHERE userName = :userName")
    suspend fun login(userName: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(users: List<UserEntity>)

    @Query("DELETE FROM user_table")
    suspend fun removeAllUsers()
}