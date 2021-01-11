package com.teamacodechallenge6.ui.menu

import com.teamacodechallenge6.data.local.SharedPref
import com.teamacodechallenge6.data.model.Users

class MainMenuPresenterImp(private val view: MainMenuView) : MainMenuPresenter{
    override fun showUsername() {
        val user = SharedPref.getUser()
        val username = user.username
        view.onSuccess("Selamat datang $username")
    }

}