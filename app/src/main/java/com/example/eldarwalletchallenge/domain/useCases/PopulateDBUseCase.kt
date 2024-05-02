package com.example.eldarwalletchallenge.domain.useCases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.eldarwalletchallenge.data.EldarWalletRepository
import com.example.eldarwalletchallenge.data.database.CardProvider
import com.example.eldarwalletchallenge.data.database.UserProvider
import com.example.eldarwalletchallenge.data.database.entities.UserEntity
import javax.inject.Inject

class PopulateDBUseCase @Inject constructor(
    private val eldarWalletRepository: EldarWalletRepository
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): Boolean {
        return try {
            eldarWalletRepository.populateCardTable(CardProvider.CARDS)
            eldarWalletRepository.populateUserTable(UserProvider.USERS)
            true
        } catch (e: Exception) {
            false
        }
    }

}