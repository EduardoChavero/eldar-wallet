package com.example.eldarwalletchallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eldarwalletchallenge.data.database.dao.CardDao
import com.example.eldarwalletchallenge.data.database.dao.UserDao
import com.example.eldarwalletchallenge.data.database.entities.CardEntity
import com.example.eldarwalletchallenge.data.database.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        CardEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class EldarWalletDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getCardDao(): CardDao

}