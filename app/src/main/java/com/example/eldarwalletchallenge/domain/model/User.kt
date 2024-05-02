package com.example.eldarwalletchallenge.domain.model

data class User(
    val id: Long = 0,
    val userName: String = "",
    val fullName: String = "",
    val balance: Double = 0.0
)
