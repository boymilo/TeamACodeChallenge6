package com.teamacodechallenge6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.android.synthetic.main.activity_profile_teman.*

class ProfileTeman : AppCompatActivity() {
    private var mDB : TemanDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_teman)
        mDB = TemanDatabase.getInstance(this)
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)


    }
}