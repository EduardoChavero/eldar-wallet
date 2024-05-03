package com.example.eldarwalletchallenge.ui.viewModels

import android.graphics.Bitmap
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwalletchallenge.domain.model.Card
import com.example.eldarwalletchallenge.domain.model.HomeAction
import com.example.eldarwalletchallenge.domain.model.User
import com.example.eldarwalletchallenge.domain.useCases.GenerateQrUseCase
import com.example.eldarwalletchallenge.domain.useCases.GetHomeActionsUseCase
import com.example.eldarwalletchallenge.domain.useCases.GetUserCardsUseCase
import com.example.eldarwalletchallenge.domain.useCases.IsCorrectCardDataUseCase
import com.example.eldarwalletchallenge.domain.useCases.LoginUseCase
import com.example.eldarwalletchallenge.domain.useCases.PopulateDBUseCase
import com.example.eldarwalletchallenge.domain.useCases.SaveNewCardUseCase
import com.example.eldarwalletchallenge.ui.reusables.Event
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
    private val saveNewCardUseCase: SaveNewCardUseCase,
    private val generateQrUseCase: GenerateQrUseCase
) : ViewModel() {

    private val _populateSuccess = MutableLiveData<Event<Boolean>>()
    val populateSuccess: LiveData<Event<Boolean>> get() = _populateSuccess

    private val _loginSuccess = MutableLiveData<Event<Boolean>>()
    val loginSuccess: LiveData<Event<Boolean>> get() = _loginSuccess

    private val _homeActions = MutableLiveData<List<HomeAction>>()
    val homeActions: LiveData<List<HomeAction>> get() = _homeActions

    private val _userCards = MutableLiveData<Event<List<Card>>>()
    val userCards: LiveData<Event<List<Card>>> get() = _userCards

    private val _addCardSuccess = MutableLiveData<Event<Boolean>>()
    val addCardSuccess: LiveData<Event<Boolean>> get() = _addCardSuccess

    private val _generateQrCodeSuccess = MutableLiveData<Event<Boolean>>()
    val generateQrCodeSuccess: LiveData<Event<Boolean>> get() = _generateQrCodeSuccess

    private var userLogged: User? = null
    private var qrCodeBitmap: Bitmap? = null

    fun populateDB() {
        viewModelScope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val populate = populateDBUseCase()
                _populateSuccess.postValue(Event(populate))
            }
        }
    }

    fun login(user: String, password: String) {
        viewModelScope.launch {
            userLogged = loginUseCase(user, password)
            _loginSuccess.postValue(Event(userLogged != null))
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
                _userCards.postValue(Event(result))
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
                _addCardSuccess.postValue(Event(false))
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
                _addCardSuccess.postValue(Event(false))
                return@launch
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val saved = saveNewCardUseCase(card)
                _addCardSuccess.postValue(Event(saved))
            }
        }
    }

    fun generateQRCode() {
        viewModelScope.launch {
            qrCodeBitmap = generateQrUseCase(userLogged?.fullName ?: "")
            _generateQrCodeSuccess.postValue(Event(qrCodeBitmap != null))
        }
    }

    fun getQrBitmap(): Bitmap? {
        return qrCodeBitmap
    }
}