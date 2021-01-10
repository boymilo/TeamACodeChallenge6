package com.teamacodechallenge6.ui.login

import com.teamacodechallenge6.data.local.SharedPref

class LoginPresenterImp(private val view: LoginView) : LoginPresenter {

    override fun login(username: String, password: String) {
        val user = SharedPref.getUser()
        if (username == user.username && password == user.password) {
            SharedPref.isLogin = true
            view.onSuccess()
        } else {
            view.onError("Username atau password salah")
        }
    }

    override fun checkIsLogin(): Boolean = SharedPref.isLogin == true
}