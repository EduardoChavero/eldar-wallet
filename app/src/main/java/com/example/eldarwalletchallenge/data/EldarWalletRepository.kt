package com.example.eldarwalletchallenge.data

import com.example.eldarwalletchallenge.data.database.HomeActionProvider
import com.example.eldarwalletchallenge.data.database.dao.UserDao
import com.example.eldarwalletchallenge.data.database.entities.UserEntity
import com.example.eldarwalletchallenge.domain.model.HomeAction
import javax.inject.Inject

class EldarWalletRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun populateUserTable(users: List<UserEntity>) {
        userDao.removeAllUsers()
        userDao.insert(users)
    }

    suspend fun login(userName: String): UserEntity {
        return userDao.login(userName)
    }

    fun getHomeActions(): List<HomeAction> {
        return HomeActionProvider.HOME_ACTIONS
    }

}