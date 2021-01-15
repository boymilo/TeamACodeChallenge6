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
        mDB = context?.let { TemanDatabase.getInstance(it) }
        GlobalScope.launch(Dispatchers.IO){
            val pemain = mDB?.pemainDao()?.getPemainById(SharedPref.id!!)
            launch(Dispatchers.Main) {
                var username = pemain?.username
                view.onSuccess("Selamat datang $username")
            }
        }

    }

}