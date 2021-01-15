package com.teamacodechallenge6.ui.signup

import android.content.Context

interface SignUpPresenter {
    fun signUp(username: String, password: String, email: String)
}