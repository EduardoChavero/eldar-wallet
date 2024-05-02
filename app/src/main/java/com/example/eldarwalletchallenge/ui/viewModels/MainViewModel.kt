package com.example.eldarwalletchallenge.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwalletchallenge.domain.model.HomeAction
import com.example.eldarwalletchallenge.domain.model.User
import com.example.eldarwalletchallenge.domain.useCases.GetHomeActionsUseCase
import com.example.eldarwalletchallenge.domain.useCases.LoginUseCase
import com.example.eldarwalletchallenge.domain.useCases.PopulateDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val populateDBUseCase: PopulateDBUseCase,
    private val loginUseCase: LoginUseCase,
    private val getHomeActionsUseCase: GetHomeActionsUseCase
) : ViewModel() {

    private val _populateSuccess = MutableLiveData<Boolean>()
    val populateSuccess: LiveData<Boolean> get() = _populateSuccess

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _homeActions = MutableLiveData<List<HomeAction>>()
    val homeActions: LiveData<List<HomeAction>> get() = _homeActions

    private var userLogged: User? = null

    fun populateDB() {
        viewModelScope.launch {
            val populate = populateDBUseCase()
            _populateSuccess.postValue(populate)
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
}