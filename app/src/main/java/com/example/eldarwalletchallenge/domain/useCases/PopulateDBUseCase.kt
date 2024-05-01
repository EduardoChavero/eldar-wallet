package com.example.eldarwalletchallenge.domain.useCases

import com.example.eldarwalletchallenge.data.EldarWalletRepository
import com.example.eldarwalletchallenge.data.database.entities.UserEntity
import javax.inject.Inject

class PopulateDBUseCase @Inject constructor(
    private val eldarWalletRepository: EldarWalletRepository
) {

    suspend operator fun invoke(): Boolean {
        try {
            val users = listOf(
                UserEntity(name = "Eduardo", password = "12345678", balance = 1320.20),
                UserEntity(name = "Alice", password = "qwerty", balance = 789.45),
                UserEntity(name = "Bob", password = "password123", balance = 2345.67),
                UserEntity(name = "Charlie", password = "abc123", balance = 987.65),
                UserEntity(name = "Diana", password = "passpass", balance = 4321.09),
                UserEntity(name = "Eva", password = "evaeva", balance = 567.89),
                UserEntity(name = "Frank", password = "frankie", balance = 1234.56),
                UserEntity(name = "Grace", password = "graceful", balance = 987.65),
                UserEntity(name = "Henry", password = "henry123", balance = 3456.78),
                UserEntity(name = "Isabel", password = "isabel23", balance = 2100.50),
                UserEntity(name = "Jack", password = "jackjack", balance = 876.54)
            )
            eldarWalletRepository.populateUserTable(users)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}