package com.teamacodechallenge6.ui.pilihLawan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamacodechallenge6.R
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.android.synthetic.main.activity_pilih_lawan.*
import kotlinx.android.synthetic.main.activity_profile_teman.*
import kotlinx.android.synthetic.main.activity_profile_teman.recyclerView

class PilihLawan : AppCompatActivity() {
    private var presenter: PilihLawanPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_lawan)
        presenter = PilihLawanPresenterImp(this)


        showLawan()

    }

    fun showLawan(){
        presenter?.ShowList(recyclerView, this@PilihLawan)
    }

    override fun onResume() {
        super.onResume()
        showLawan()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.DestroyDB()
    }
}