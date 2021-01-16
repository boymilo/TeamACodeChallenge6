package com.teamacodechallenge6.ui.signup

import com.teamacodechallenge6.App.Companion.context
import com.teamacodechallenge6.App.Companion.mDB
import com.teamacodechallenge6.database.Pemain
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpPresenterImp(private val view: SignUpView): SignUpPresenter{
    override fun signUp(username: String, password: String, email: String) {
        mDB = context?.let { TemanDatabase.getInstance(it) }
        val pemainBaru = Pemain(null, username, password, email)
        GlobalScope.launch(Dispatchers.IO) {
            val checker = mDB?.pemainDao()?.getPemainByUsername(username)
            if(checker == null){
                mDB?.pemainDao()?.insertPemain(pemainBaru)
            }
            launch(Dispatchers.Main) {
                if (checker == null){
                    view.onSuccess()
                }
                else{
                    view.onError("Username telah digunakan")
                }
            }
        }
    }
}