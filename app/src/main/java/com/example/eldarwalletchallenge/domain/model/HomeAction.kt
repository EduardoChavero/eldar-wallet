package com.example.eldarwalletchallenge.domain.model

data class HomeAction(
    val type: HomeActionTypes = HomeActionTypes.DEFAULT,
    val label: String = "",
    val icon: Int = 0
)