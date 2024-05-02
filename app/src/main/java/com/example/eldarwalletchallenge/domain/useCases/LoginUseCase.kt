package com.example.eldarwalletchallenge.domain.useCases

import com.example.eldarwalletchallenge.data.EldarWalletRepository
import com.example.eldarwalletchallenge.domain.model.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val eldarWalletRepository: EldarWalletRepository
) {

    suspend operator fun invoke(userName: String, password: String): User? {
        try {
            val users = eldarWalletRepository.getUsers()

            val userData = users.find {
                it.userName.lowercase() == userName.lowercase()
            }

            if (userData?.password == password) {
                return User(
                    id = userData.id,
                    userName = userData.userName,
                    fullName = userData.fullName,
                    balance = userData.balance
                )
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }

}