package com.teamacodechallenge6.ui.pilihLawan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamacodechallenge6.R
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.android.synthetic.main.activity_pilih_lawan.*
import kotlinx.android.synthetic.main.activity_profile_teman.*
import kotlinx.android.synthetic.main.activity_profile_teman.recyclerView
import kotlinx.android.synthetic.main.activity_profile_teman.tvDaftar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DaftarPemain : AppCompatActivity() {
    private var mDB : TemanDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_lawan)

        tvDaftar.setText("Daftar Pemain")
        mDB = TemanDatabase.getInstance(this)
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)
        fetchData()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData(){
        GlobalScope.launch {
            val listPemain = mDB?.pemainDao()?.getAllPemain()
            runOnUiThread {
                listPemain?.let {
                    val adapter = DaftarPemainAdapter(listPemain, this@DaftarPemain)
                    recyclerView.adapter = adapter
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TemanDatabase.destroyInstance()
    }
}