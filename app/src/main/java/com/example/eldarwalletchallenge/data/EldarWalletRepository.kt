package com.example.eldarwalletchallenge.data

import com.example.eldarwalletchallenge.data.database.HomeActionProvider
import com.example.eldarwalletchallenge.data.database.dao.CardDao
import com.example.eldarwalletchallenge.data.database.dao.UserDao
import com.example.eldarwalletchallenge.data.database.entities.CardEntity
import com.example.eldarwalletchallenge.data.database.entities.UserEntity
import com.example.eldarwalletchallenge.domain.model.HomeAction
import javax.inject.Inject

class EldarWalletRepository @Inject constructor(
    private val userDao: UserDao,
    private val cardDao: CardDao
) {

    suspend fun populateUserTable(users: List<UserEntity>) {
        userDao.insert(users)
    }

    suspend fun populateCardTable(cards: List<CardEntity>) {
        cardDao.insert(cards)
    }

    suspend fun login(userName: String): UserEntity {
        return userDao.login(userName)
    }

    fun getHomeActions(): List<HomeAction> {
        return HomeActionProvider.HOME_ACTIONS
    }

    suspend fun getUserCards(userId: Long): List<CardEntity> {
        return cardDao.getUserCards(userId)
    }

}