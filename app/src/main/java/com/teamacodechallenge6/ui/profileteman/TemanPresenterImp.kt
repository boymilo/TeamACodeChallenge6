package com.teamacodechallenge6.ui.profileteman

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.App
import com.teamacodechallenge6.App.Companion.context
import com.teamacodechallenge6.App.Companion.mDB
import com.teamacodechallenge6.data.local.SharedPref
import com.teamacodechallenge6.database.Teman
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TemanPresenterImp(private val view: TemanView) : TemanPresenter {
    override fun playerName() {
        mDB = context?.let { TemanDatabase.getInstance(it) }
        GlobalScope.launch(Dispatchers.IO){
            val pemain = SharedPref.id?.let { mDB?.pemainDao()?.getPemainById(it) }
            launch(Dispatchers.Main) {
                val username = pemain?.username.toString()
                val email=pemain?.email.toString()
                view.nameEmail(username,email)
            }
        }
    }

    override fun addTeman(name: String, email: String) {
        mDB = context?.let { TemanDatabase.getInstance(it) }
        val objectTeman = Teman(null, name, email)
        GlobalScope.launch(Dispatchers.IO) {
           val result=mDB?.temanDao()?.insertTeman(objectTeman)
            launch(Dispatchers.Main) {
                if (result!=0.toLong()){
                    view.onSuccessTeman("Teman kamu $name berhasil ditambahakan")
                }
                else{
                    view.onFailedTeman("Teman kamu $name gagal ditambahakan")
                }
            }

        }
    }

    override fun listTeman(recyclerView: RecyclerView, context: Context) {
        mDB = App.context?.let { TemanDatabase.getInstance(it) }
        GlobalScope.launch (Dispatchers.IO){
            val listTeman = mDB?.temanDao()?.getAllTeman()
            launch(Dispatchers.Main){
                listTeman?.let {
                    val adapter = TemanAdapter(listTeman, context)
                    recyclerView.adapter = adapter
                }
            }
        }
    }

    override fun deleteTeman(list: List<Teman>,position:Int) {
        GlobalScope.launch (Dispatchers.IO){
           val result= mDB?.temanDao()?.deleteTeman(list[position])
            launch (Dispatchers.Main){
                if (result != 0) {
                    view.onSuccessTeman("Teman kamu berhasil dihapus")
                } else {
                    view.onFailedTeman("Teman kamu gagal dihapus")
                }
            }
        }
    }

    override fun editTeman(list: List<Teman>, position: Int) {
        GlobalScope.launch (Dispatchers.IO){
            val result= mDB?.temanDao()?.updateTeman(list[position])
            launch(Dispatchers.Main) {
                if (result != 0) {
                    view.onSuccessTeman("Teman kamu berhasil diubah")
                } else {
                    view.onSuccessTeman("Teman kamu gagal diubah")
                }
            }
        }
    }

    override fun DestroyDB() {
        TemanDatabase.destroyInstance()
    }
}