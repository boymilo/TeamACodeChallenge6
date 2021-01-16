package com.teamacodechallenge6.ui.pilihLawan

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PilihLawanPresenterImp : PilihLawanPresenter {
//    override fun signUp(username: String, password: String, email: String) {
//        val user = Users(username, password, email)
//        SharedPref?.mappingTopPref(user)
//        view.onSuccess()
//    }

    override fun showList(recyclerView: RecyclerView, context: Context) {
        val mDB = TemanDatabase.getInstance(context)
        recyclerView.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        GlobalScope.launch(Dispatchers.IO) {
            val listTeman = mDB?.temanDao()?.getAllTeman()

            launch(Dispatchers.Main) {
                listTeman?.let {
                    val adapter = PilihLawanAdapter(listTeman, context)
                    recyclerView.adapter = adapter
                }
            }
        }
    }

    override fun destroyDB() {
        TemanDatabase.destroyInstance()
    }
}