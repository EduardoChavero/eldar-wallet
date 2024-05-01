package com.example.eldarwalletchallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eldarwalletchallenge.data.database.dao.UserDao
import com.example.eldarwalletchallenge.data.database.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class EldarWalletDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

}