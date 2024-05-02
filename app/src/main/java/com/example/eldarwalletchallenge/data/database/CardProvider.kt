package com.example.eldarwalletchallenge.data.database

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.eldarwalletchallenge.data.database.entities.CardEntity
import com.example.eldarwalletchallenge.ui.reusables.EncryptUtil

class CardProvider {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val CARDS = listOf(
            CardEntity(
                ownerId = 1,
                ownerName = "Eduardo Angel Chavero",
                cardNumber = EncryptUtil.encrypt("5031755734530604"),
                expirationDate = "11/25",
                cvv = EncryptUtil.encrypt("123"),
            ),
            CardEntity(
                ownerId = 1,
                ownerName = "Eduardo Angel Chavero",
                cardNumber = EncryptUtil.encrypt("4509953566233704"),
                expirationDate = "11/25",
                cvv = EncryptUtil.encrypt("123"),
            ),
            CardEntity(
                ownerId = 1,
                ownerName = "Eduardo Angel Chavero",
                cardNumber = EncryptUtil.encrypt("371180303257522"),
                expirationDate = "11/25",
                cvv = EncryptUtil.encrypt("1234"),
            ),
            CardEntity(
                ownerId = 2,
                ownerName = "Alice Smith",
                cardNumber = EncryptUtil.encrypt("4916326315818417"),
                expirationDate = "11/24",
                cvv = EncryptUtil.encrypt("456"),
            ),
            CardEntity(
                ownerId = 3,
                ownerName = "Bob Johnson",
                cardNumber = EncryptUtil.encrypt("4532096699144765"),
                expirationDate = "05/24",
                cvv = EncryptUtil.encrypt("789"),
            ),
        )
    }
}