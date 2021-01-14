package com.teamacodechallenge6.ui.signup

import com.teamacodechallenge6.App
import com.teamacodechallenge6.data.local.SharedPref
import com.teamacodechallenge6.data.model.Users
import com.teamacodechallenge6.database.Pemain
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpPresenterImp(private val view: SignUpView): SignUpPresenter{
    override fun signUp(username: String, password: String, email: String) {
        val pemain = Pemain(null,username, password, email)
        App.mDB = App.context?.let { TemanDatabase.getInstance(it) }
        GlobalScope.launch {
            App.mDB?.pemainDao()?.insertPemain(pemain)
        }
        view.onSuccess()
    }
}