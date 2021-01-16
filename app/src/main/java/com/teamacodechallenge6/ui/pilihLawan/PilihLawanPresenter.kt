package com.teamacodechallenge6.ui.pilihLawan

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

interface PilihLawanPresenter {
    fun showList(recyclerView: RecyclerView, context: Context)
    fun destroyDB()
}