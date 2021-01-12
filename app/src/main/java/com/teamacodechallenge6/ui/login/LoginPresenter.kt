package com.teamacodechallenge6.ui.login

import android.content.Context

interface LoginPresenter {
//    fun login(username: String, password: String)
    fun login(username: String, password: String, context: Context)//coba login dgn database
    fun checkIsLogin(): Boolean
}