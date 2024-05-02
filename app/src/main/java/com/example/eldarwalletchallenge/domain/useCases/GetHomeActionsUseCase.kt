package com.example.eldarwalletchallenge.domain.useCases

import com.example.eldarwalletchallenge.data.EldarWalletRepository
import com.example.eldarwalletchallenge.domain.model.HomeAction
import javax.inject.Inject

class GetHomeActionsUseCase @Inject constructor(
    private val eldarWalletRepository: EldarWalletRepository
) {

    operator fun invoke(): List<HomeAction> {
        return eldarWalletRepository.getHomeActions()
    }
}