package com.teamacodechallenge6.ui.profileteman

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.App
import com.teamacodechallenge6.App.Companion.context
import com.teamacodechallenge6.App.Companion.mDB
import com.teamacodechallenge6.database.Teman
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.coroutines.GlobalScope
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

    override fun deleteTeman(list: List<Teman>,position:Int) {
        GlobalScope.launch {
           val result= mDB?.temanDao()?.deleteTeman(list[position])
            launch {
                if (result != 0) {
                    view.onSuccessAddTeman()
                } else {
                    view.onSuccessAddTeman()
                }
            }
        }
    }

    override fun editTeman(list: List<Teman>, position: Int) {
        GlobalScope.launch {
            val result= mDB?.temanDao()?.updateTeman(list[position])
            launch {
                if (result != 0) {
                    view.onSuccessAddTeman()
                } else {
                    view.onSuccessAddTeman()
                }
            }
        }
    }

    override fun DestroyDB() {
        TemanDatabase.destroyInstance()
    }
}