package com.teamacodechallenge6.data.local

import android.content.Context
import com.teamacodechallenge6.App
import com.teamacodechallenge6.data.model.Users

object SharedPref {
    private const val KEY_USERNAME = "KEY_USERNAME"
    private const val KEY_PASSWORD = "KEY_PASSWORD"
    private const val KEY_EMAIL = "KEY_EMAIL"
    private const val KEY_ISLOGIN = "KEY_ISLOGIN"

    private val pref = App.context?.getSharedPreferences("CodeChallenge6", Context.MODE_PRIVATE)

    fun mappingTopPref(users: Users){
        pref?.edit()
            ?.putString(KEY_USERNAME, users.username)
            ?.apply()

        pref?.edit()
            ?.putString(KEY_PASSWORD, users.password)
            ?.apply()

        pref?.edit()
            ?.putString(KEY_EMAIL, users.email)
            ?.apply()

    }

    fun getUser(): Users {
        val username = pref?.getString(KEY_USERNAME, "").toString()
        val password = pref?.getString(KEY_PASSWORD, "").toString()
        val email = pref?.getString(KEY_EMAIL, "").toString()
        return Users(username, password, email)
    }

    var isLogin: Boolean?
        get() = pref?.getBoolean(KEY_ISLOGIN, false)
        set(value) {
            value?.let{
                pref?.edit()
                    ?.putBoolean(KEY_ISLOGIN, it)
                    ?.apply()
            }
        }

}