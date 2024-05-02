package com.example.eldarwalletchallenge.data.database

import com.example.eldarwalletchallenge.R
import com.example.eldarwalletchallenge.domain.model.HomeAction
import com.example.eldarwalletchallenge.domain.model.HomeActionTypes

class HomeActionProvider {
    companion object {
        val HOME_ACTIONS = listOf(
            HomeAction(
                type = HomeActionTypes.GENERATE_QR,
                label = "Generar QR",
                icon = R.drawable.ic_qr
            ),
            HomeAction(
                type = HomeActionTypes.ADD_CARD,
                label = "Agregar Tarjeta",
                icon = R.drawable.ic_add_card
            ),
            HomeAction(
                type = HomeActionTypes.PAYMENT,
                label = "Pagar",
                icon = R.drawable.ic_nfc
            ),
        )
    }
}