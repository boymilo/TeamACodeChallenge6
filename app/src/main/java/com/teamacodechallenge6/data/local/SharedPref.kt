package com.teamacodechallenge6.data.local

import android.content.Context
import com.teamacodechallenge6.App
import com.teamacodechallenge6.data.model.Users

object SharedPref {
    private const val KEY_ISLOGIN = "KEY_ISLOGIN"
    private const val KEY_ID = "KEY_ID"

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
    var id: Int?
        get()  = pref?.getInt(KEY_ID, 0)
        set(value) {
            value?.let {
                pref?.edit()
                    ?.putInt(KEY_ID, it)
                    ?.apply()
            }
        }
}