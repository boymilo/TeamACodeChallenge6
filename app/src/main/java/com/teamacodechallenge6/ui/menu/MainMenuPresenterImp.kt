package com.teamacodechallenge6.ui.menu

import com.teamacodechallenge6.App.Companion.context
import com.teamacodechallenge6.App.Companion.mDB
import com.teamacodechallenge6.data.local.SharedPref
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainMenuPresenterImp(private val view: MainMenuView) : MainMenuPresenter{

    override fun showUsername() {
        val username = SharedPref.username

        if (username != null) {
            view.onSuccess(username)
        }
    }

    override fun logout() {
        SharedPref.isLogin=false
    }

}