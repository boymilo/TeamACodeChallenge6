package com.teamacodechallenge6.ui.login

import android.content.Context
import com.teamacodechallenge6.data.local.SharedPref
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenterImp(private val view: LoginView) : LoginPresenter {


    override fun login(username: String, password: String, context: Context) {
        val mDB = TemanDatabase.getInstance(context)

        GlobalScope.launch {
            val pemain = mDB?.pemainDao()?.getPemain(username)
            if (pemain == null) {
                view.onError("username belum terdaftar")
            } else {
                var passwordDB = pemain.password
                if (password != passwordDB)
                    view.onError("password salah")
                else{
                    view.onSuccess()
                    SharedPref.username = username
                    SharedPref.isLogin = true
                }
            }
        }

    }
}