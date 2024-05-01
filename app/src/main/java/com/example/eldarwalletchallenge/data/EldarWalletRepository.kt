package com.example.eldarwalletchallenge.data

import com.example.eldarwalletchallenge.data.database.dao.UserDao
import com.example.eldarwalletchallenge.data.database.entities.UserEntity
import javax.inject.Inject

class EldarWalletRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun populateUserTable(users: List<UserEntity>) {
        userDao.insert(users)
    }

}