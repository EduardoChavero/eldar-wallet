package com.example.eldarwalletchallenge.ui.viewModels

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwalletchallenge.domain.model.Card
import com.example.eldarwalletchallenge.domain.model.HomeAction
import com.example.eldarwalletchallenge.domain.model.User
import com.example.eldarwalletchallenge.domain.useCases.GetHomeActionsUseCase
import com.example.eldarwalletchallenge.domain.useCases.GetUserCardsUseCase
import com.example.eldarwalletchallenge.domain.useCases.IsCorrectCardDataUseCase
import com.example.eldarwalletchallenge.domain.useCases.LoginUseCase
import com.example.eldarwalletchallenge.domain.useCases.PopulateDBUseCase
import com.example.eldarwalletchallenge.domain.useCases.SaveNewCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val populateDBUseCase: PopulateDBUseCase,
    private val loginUseCase: LoginUseCase,
    private val getHomeActionsUseCase: GetHomeActionsUseCase,
    private val getUserCardsUseCase: GetUserCardsUseCase,
    private val isCorrectCardDataUseCase: IsCorrectCardDataUseCase,
    private val saveNewCardUseCase: SaveNewCardUseCase
) : ViewModel() {

    private val _populateSuccess = MutableLiveData<Boolean>()
    val populateSuccess: LiveData<Boolean> get() = _populateSuccess

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _homeActions = MutableLiveData<List<HomeAction>>()
    val homeActions: LiveData<List<HomeAction>> get() = _homeActions

    private val _userCards = MutableLiveData<List<Card>>()
    val userCards: LiveData<List<Card>> get() = _userCards

    private val _addCardSuccess = MutableLiveData<Boolean>()
    val addCardSuccess: LiveData<Boolean> get() = _addCardSuccess

    private var userLogged: User? = null

    fun populateDB() {
        viewModelScope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val populate = populateDBUseCase()
                _populateSuccess.postValue(populate)
            }
        }
    }

    fun login(user: String, password: String) {
        viewModelScope.launch {
            userLogged = loginUseCase(user, password)
            _loginSuccess.postValue(userLogged != null)
        }
    }

    fun getUserBalance(): String {
        val balance = userLogged?.balance ?: return ""
        return balance.toString()
    }

    fun getUserFistName(): String {
        val fullName = userLogged?.fullName ?: return ""
        return fullName.split(" ")[0]
    }

    fun getHomeActions() {
        val result = getHomeActionsUseCase()
        _homeActions.postValue(result)
    }

    fun getUserCards() {
        viewModelScope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val result = getUserCardsUseCase(userLogged?.id ?: 0)
                _userCards.postValue(result)
            }
        }
    }

    fun addCardRequest(
        cardNumber: String,
        ownerName: String,
        expirationDate: String,
        cvv: String
    ) {
        viewModelScope.launch {
            if (ownerName.lowercase() != userLogged?.fullName?.lowercase()) {
                _addCardSuccess.postValue(false)
                return@launch
            }
            val card = Card(
                ownerId = userLogged?.id ?: -1,
                ownerName = ownerName,
                cardNumber = cardNumber,
                expirationDate = expirationDate,
                cvv = cvv,
            )
            if (!isCorrectCardDataUseCase(card)) {
                _addCardSuccess.postValue(false)
                return@launch
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val saved = saveNewCardUseCase(card)
                _addCardSuccess.postValue(saved)
            }
        }
    }
}