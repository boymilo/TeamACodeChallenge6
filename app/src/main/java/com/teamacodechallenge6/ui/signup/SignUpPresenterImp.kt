package com.teamacodechallenge6.ui.signup

import com.teamacodechallenge6.data.local.SharedPref
import com.teamacodechallenge6.data.model.Users

class SignUpPresenterImp(private val view: SignUpView): SignUpPresenter{
    override fun signUp(username: String, password: String, email: String) {
        val user = Users(username, password, email)
        SharedPref?.mappingTopPref(user)
        view.onSuccess()
    }
}