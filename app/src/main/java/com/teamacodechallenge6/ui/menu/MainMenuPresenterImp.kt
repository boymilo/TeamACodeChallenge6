package com.teamacodechallenge6.ui.menu

import com.teamacodechallenge6.data.local.SharedPref

class MainMenuPresenterImp(private val view: MainMenuView) : MainMenuPresenter{

    override fun showUsername() {
        var username = SharedPref.username
        view.onSuccess("Selamat datang $username")
    }

}