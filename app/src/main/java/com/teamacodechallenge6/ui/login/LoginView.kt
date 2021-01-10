package com.teamacodechallenge6.ui.login

interface LoginView {
    fun onSuccess()
    fun onError(msg: String)
}