package com.example.eldarwalletchallenge.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private var userName: String = ""

    fun login(user: String, password: String) {
        userName = user
        _loginSuccess.postValue(true)
    }

}