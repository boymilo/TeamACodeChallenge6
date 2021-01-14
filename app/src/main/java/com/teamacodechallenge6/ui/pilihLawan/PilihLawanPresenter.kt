package com.teamacodechallenge6.ui.pilihLawan

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

interface PilihLawanPresenter {
//    fun signUp(username: String, password: String, email: String)
    fun ShowList(recyclerView: RecyclerView, context: Context)
    fun DestroyDB()
}