package com.teamacodechallenge6.ui.pilihLawan

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.android.synthetic.main.activity_profile_teman.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PilihLawanPresenterImp(private val view: PilihLawan): PilihLawanPresenter{
//    override fun signUp(username: String, password: String, email: String) {
//        val user = Users(username, password, email)
//        SharedPref?.mappingTopPref(user)
//        view.onSuccess()
//    }

    override fun ShowList(recyclerView:RecyclerView, context: Context) {
        var mDB = TemanDatabase.getInstance(context)
        recyclerView.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false)
        GlobalScope.launch (Dispatchers.IO){
            val listTeman = mDB?.temanDao()?.getAllTeman()

            launch (Dispatchers.Main){
                listTeman?.let {
                    val adapter = PilihLawanAdapter(listTeman, context)
                    recyclerView.adapter = adapter
                }
            }
        }
    }

    override fun DestroyDB() {
        TemanDatabase.destroyInstance()
    }
}