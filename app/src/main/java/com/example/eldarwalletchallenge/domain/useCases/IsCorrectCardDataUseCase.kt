package com.example.eldarwalletchallenge.domain.useCases

import com.example.eldarwalletchallenge.domain.model.Card
import com.example.eldarwalletchallenge.ui.reusables.GetResourceUtil
import javax.inject.Inject

class IsCorrectCardDataUseCase @Inject constructor() {

    companion object {
        const val DIGIT_NUMBER_REQUIRED_AMEX = 15
        const val DIGIT_NUMBER_REQUIRED_DEFAULT = 16
        const val DIGIT_CVV_REQUIRED_AMEX = 4
        const val DIGIT_CVV_REQUIRED_DEFAULT = 3
    }

    operator fun invoke(card: Card): Boolean {
        var digitsNumberRequired = DIGIT_NUMBER_REQUIRED_DEFAULT
        var digitCvvRequired = DIGIT_CVV_REQUIRED_DEFAULT

        if (card.cardNumber.first().toString() == GetResourceUtil.AMEX_INITIAL_DIGIT) {
            digitsNumberRequired = DIGIT_NUMBER_REQUIRED_AMEX
            digitCvvRequired = DIGIT_CVV_REQUIRED_AMEX
        }

        if (card.cardNumber.length != digitsNumberRequired) return false
        if (card.cvv.length != digitCvvRequired) return false

        return true

    }

}