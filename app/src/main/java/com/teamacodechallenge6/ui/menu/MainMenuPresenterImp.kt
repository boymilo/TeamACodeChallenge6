package com.teamacodechallenge6.ui.menu

import com.teamacodechallenge6.data.local.SharedPref

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