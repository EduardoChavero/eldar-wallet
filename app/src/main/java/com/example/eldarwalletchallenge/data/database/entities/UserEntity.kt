package com.example.eldarwalletchallenge.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user_table", indices = [Index(value = ["userName"], unique = true)])
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userName: String = "",
    val fullName: String = "",
    val password: String = "",
    val balance: Double = 0.0
)
