package com.example.eldarwalletchallenge.domain.useCases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.eldarwalletchallenge.R
import com.example.eldarwalletchallenge.data.EldarWalletRepository
import com.example.eldarwalletchallenge.data.database.entities.CardEntity
import com.example.eldarwalletchallenge.domain.model.Card
import com.example.eldarwalletchallenge.ui.reusables.EncryptUtil
import com.example.eldarwalletchallenge.ui.reusables.GetResourceUtil
import javax.inject.Inject

class GetUserCardsUseCase @Inject constructor(
    private val eldarWalletRepository: EldarWalletRepository
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(userId: Long): List<Card> {
        val cardsData = eldarWalletRepository.getUserCards(userId)
        return parseCardData(cardsData)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseCardData(cardsData: List<CardEntity>): List<Card> {
        val cards = mutableListOf<Card>()
        for (cardItem in cardsData) {
            val cardNumber = EncryptUtil.decrypt(cardItem.cardNumber)
            cards.add(
                Card(
                    id = cardItem.id,
                    ownerName = cardItem.ownerName,
                    lastNumbers = cardNumber.takeLast(4),
                    expirationDate = cardItem.expirationDate,
                    logoRes = GetResourceUtil.getLogo(cardNumber.first().toString()),
                )
            )
        }
        return cards
    }

}