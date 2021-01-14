package com.teamacodechallenge6.data.local

import android.content.Context
import com.teamacodechallenge6.App
import com.teamacodechallenge6.data.model.Users

object SharedPref {
    private const val KEY_ISLOGIN = "KEY_ISLOGIN"
    private const val KEY_ID = "KEY_USERNAME"

    private val pref = App.context?.getSharedPreferences("CodeChallenge6", Context.MODE_PRIVATE)

    var isLogin: Boolean?
        get() = pref?.getBoolean(KEY_ISLOGIN, false)
        set(value) {
            value?.let{
                pref?.edit()
                    ?.putBoolean(KEY_ISLOGIN, it)
                    ?.apply()
            }
        }
    var username: String?
        get()  = pref?.getString(KEY_ID, "")
        set(value) {
            value?.let {
                pref?.edit()
                    ?.putString(KEY_ID, it)
                    ?.apply()
            }
        }
}