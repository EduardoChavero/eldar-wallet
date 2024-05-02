package com.example.eldarwalletchallenge.domain.useCases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.eldarwalletchallenge.data.EldarWalletRepository
import com.example.eldarwalletchallenge.data.database.entities.CardEntity
import com.example.eldarwalletchallenge.domain.model.Card
import com.example.eldarwalletchallenge.ui.reusables.EncryptUtil
import javax.inject.Inject

class SaveNewCardUseCase @Inject constructor(
    private val eldarWalletRepository: EldarWalletRepository
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(card: Card): Boolean {
        return try {
            eldarWalletRepository.saveNewCard(parseCardData(card))
            true
        } catch (e: Exception) {
            false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseCardData(card: Card): CardEntity {
        return CardEntity(
            ownerId = card.ownerId,
            ownerName = card.ownerName,
            cardNumber = EncryptUtil.encrypt(card.cardNumber),
            expirationDate = card.expirationDate,
            cvv = EncryptUtil.encrypt(card.cvv),
        )
    }

}