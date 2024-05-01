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
                UserEntity(
                    userName = "Eduardo",
                    fullName = "Eduardo Angel Chavero",
                    password = "12345678",
                    balance = 1320.20
                ),
                UserEntity(
                    userName = "Alice",
                    fullName = "Alice Smith",
                    password = "password123",
                    balance = 2300.50
                ),
                UserEntity(
                    userName = "Bob",
                    fullName = "Bob Johnson",
                    password = "bob123",
                    balance = 800.00
                ),
                UserEntity(
                    userName = "Charlie",
                    fullName = "Charlie Brown",
                    password = "charlie456",
                    balance = 1500.75
                ),
                UserEntity(
                    userName = "Diana",
                    fullName = "Diana Rodriguez",
                    password = "diana789",
                    balance = 2100.30
                ),
                UserEntity(
                    userName = "Emma",
                    fullName = "Emma Wilson",
                    password = "emma1234",
                    balance = 1800.00
                ),
                UserEntity(
                    userName = "Frank",
                    fullName = "Frank Garcia",
                    password = "frank567",
                    balance = 3000.60
                ),
                UserEntity(
                    userName = "Grace",
                    fullName = "Grace Lee",
                    password = "grace999",
                    balance = 500.00
                ),
                UserEntity(
                    userName = "Henry",
                    fullName = "Henry Martinez",
                    password = "henry777",
                    balance = 2700.25
                ),
                UserEntity(
                    userName = "Isabel",
                    fullName = "Isabel Perez",
                    password = "isabel321",
                    balance = 400.00
                )
            )

            eldarWalletRepository.populateUserTable(users)
            return true
        } catch (e: Exception) {
            return false
        }
    }

}