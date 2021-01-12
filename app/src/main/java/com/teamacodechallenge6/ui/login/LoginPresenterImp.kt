package com.teamacodechallenge6.ui.login

import android.content.Context
import com.teamacodechallenge6.data.local.SharedPref
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenterImp(private val view: LoginView) : LoginPresenter {

    //    override fun login(username: String, password: String) {
    override fun login(username: String, password: String, context: Context) {
//        val user = SharedPref.getUser()
//        if (username == user.username && password == user.password) {
//            SharedPref.isLogin = true
//            view.onSuccess()
//        } else {
//            view.onError("Username atau password salah")
//        }

        var mDB = TemanDatabase.getInstance(context) // coba database

        GlobalScope.launch {
//            //======= coba pake database ============================

            val pemain = mDB?.pemainDao()?.getPemain(username)
            if (pemain == null) {
                view.onError("username belum terdaftar")
            } else {
                var passwordDB = pemain?.password
                if (password != passwordDB)
                    view.onError("password salah")
                else
                    view.onSuccess()
            }
//            //======= coba pake database ============================
        }

    }

    override fun checkIsLogin(): Boolean = SharedPref.isLogin == true
}