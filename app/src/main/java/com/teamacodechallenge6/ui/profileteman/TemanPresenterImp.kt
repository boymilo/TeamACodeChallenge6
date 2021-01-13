package com.teamacodechallenge6.ui.profileteman

import android.widget.Toast
import com.teamacodechallenge6.App
import com.teamacodechallenge6.App.Companion.context
import com.teamacodechallenge6.App.Companion.mDB
import com.teamacodechallenge6.data.local.SharedPref
import com.teamacodechallenge6.data.model.Users
import com.teamacodechallenge6.database.Teman
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.android.synthetic.main.activity_profile_teman.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TemanPresenterImp(private val view: TemanView) : TemanPresenter {
    override fun addTeman(name: String, email: String) {
        mDB = context?.let { TemanDatabase.getInstance(it) }
        val objectTeman = Teman(null, name, email)
        GlobalScope.launch {
           mDB?.temanDao()?.insertTeman(objectTeman)
        }
       /* if (name != ""    && email != "") {
            view.onSuccess(name)
        } else {
            view.onFailed()
        }*/
    }
}