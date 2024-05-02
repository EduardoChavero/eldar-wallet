package com.example.eldarwalletchallenge.domain.model

data class Card(
    val id: Long = 0,
    val ownerName: String = "",
    val cardNumber: String = "",
    val lastNumbers: String = "",
    val expirationDate: String = "",
    val cvv: String = "",
    val logoRes: Int = 0
)