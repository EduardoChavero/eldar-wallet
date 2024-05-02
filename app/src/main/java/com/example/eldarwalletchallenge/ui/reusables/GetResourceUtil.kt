package com.example.eldarwalletchallenge.ui.reusables

import com.example.eldarwalletchallenge.R

object GetResourceUtil {

    const val VISA_INITIAL_DIGIT = "4"
    const val MASTER_CARD_INITIAL_DIGIT = "5"
    const val AMEX_INITIAL_DIGIT = "3"

    fun getLogo(initialDigit: String): Int {
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