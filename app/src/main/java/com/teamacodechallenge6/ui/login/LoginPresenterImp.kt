package com.teamacodechallenge6.ui.login

import com.teamacodechallenge6.App.Companion.context
import com.teamacodechallenge6.App.Companion.mDB
import com.teamacodechallenge6.data.local.SharedPref
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenterImp(private val view: LoginView) : LoginPresenter {


    override fun login(username: String, password: String) {
        mDB = context?.let { TemanDatabase.getInstance(it) }

        GlobalScope.launch(Dispatchers.IO) {
            val pemain = mDB?.pemainDao()?.getPemainByUsername(username)

            launch(Dispatchers.Main) {
                if (pemain == null) {
                    view.onError("Username belum terdaftar")
                } else {
                    val passwordDB = pemain.password
                    if (password != passwordDB)
                        view.onError("Password salah")
                    else{
                        SharedPref.id = pemain.id
                        SharedPref.username = pemain.username
                        SharedPref.isLogin = true
                        view.onSuccess()
                    }
                }
            }
        }

    }
}