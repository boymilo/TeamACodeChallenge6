package com.teamacodechallenge6.ui.profileteman

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.App
import com.teamacodechallenge6.App.Companion.context
import com.teamacodechallenge6.App.Companion.mDB
import com.teamacodechallenge6.database.Teman
import com.teamacodechallenge6.database.TemanDatabase
import com.teamacodechallenge6.ui.pilihLawan.PilihLawan
import com.teamacodechallenge6.ui.pilihLawan.PilihLawanAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TemanPresenterImp(private val view: TemanView) : TemanPresenter {
    override fun addTeman(name: String, email: String) {
        mDB = context?.let { TemanDatabase.getInstance(it) }
        val objectTeman = Teman(null, name, email)
        GlobalScope.launch {
           val result=mDB?.temanDao()?.insertTeman(objectTeman)
            if (result!=0.toLong()){
                view.onSuccessAddTeman()
            }
           /* launch {

            }*/

            /*else{
                view.onFailedAddTeman()
            }*/
        }
    }

    override fun listTeman(recyclerView: RecyclerView, context: Context) {
        mDB = App.context?.let { TemanDatabase.getInstance(it) }
        GlobalScope.launch {
            val listTeman = mDB?.temanDao()?.getAllTeman()
            (context as ProfileTeman).runOnUiThread {
                listTeman?.let {
                    val adapter = TemanAdapter(listTeman, context)
                    recyclerView.adapter = adapter
                }
            }
        }
    }
    override fun DestroyDB() {
        TemanDatabase.destroyInstance()
    }
}