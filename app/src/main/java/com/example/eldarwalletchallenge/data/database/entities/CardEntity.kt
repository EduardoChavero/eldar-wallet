package com.example.eldarwalletchallenge.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "cards_table", indices = [Index(value = ["cardNumber"], unique = true)])
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val ownerId: Long = 0,
    val ownerName: String = "",
    val cardNumber: String = "",
    val expirationDate: String = "",
    val cvv: String = "",
)