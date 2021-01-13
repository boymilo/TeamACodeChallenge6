package com.teamacodechallenge6.ui.profileteman

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.database.Teman

interface TemanPresenter {
    fun addTeman(name: String, email: String)
    fun listTeman(recyclerView: RecyclerView, context: Context)
    fun DestroyDB()
}