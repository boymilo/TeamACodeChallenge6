package com.teamacodechallenge6.ui.login

interface LoginPresenter {
    fun login(username: String, password: String)
    fun checkIsLogin(): Boolean
}