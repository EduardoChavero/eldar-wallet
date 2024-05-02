package com.example.eldarwalletchallenge.domain.useCases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.eldarwalletchallenge.R
import com.example.eldarwalletchallenge.data.EldarWalletRepository
import com.example.eldarwalletchallenge.data.database.entities.CardEntity
import com.example.eldarwalletchallenge.domain.model.Card
import com.example.eldarwalletchallenge.ui.reusables.EncryptUtil
import javax.inject.Inject

class GetUserCardsUseCase @Inject constructor(
    private val eldarWalletRepository: EldarWalletRepository
) {

    companion object {
        const val VISA_INITIAL_DIGIT = "4"
        const val MASTER_CARD_INITIAL_DIGIT = "5"
        const val AMEX_INITIAL_DIGIT = "3"
    }

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
                    logoRes = getLogo(cardNumber.first().toString()),
                )
            )
        }
        return cards
    }

    private fun getLogo(initialDigit: String): Int {
        return when (initialDigit) {
            VISA_INITIAL_DIGIT -> {
                R.drawable.ic_visa_logo
            }

            MASTER_CARD_INITIAL_DIGIT -> {
                R.drawable.ic_master_logo
            }

            AMEX_INITIAL_DIGIT -> {
                R.drawable.ic_amex_logo
            }

            else -> {
                0
            }
        }

    }
}