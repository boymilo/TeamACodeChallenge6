package com.teamacodechallenge6.ui.profileteman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.teamacodechallenge6.R
import com.teamacodechallenge6.database.Teman
import com.teamacodechallenge6.database.TemanDatabase
import kotlinx.android.synthetic.main.activity_profile_teman.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ProfileTeman : AppCompatActivity() {
    private var mDB : TemanDatabase? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_teman)

        mDB = TemanDatabase.getInstance(this)
        recyclerView?.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)
        fetchData()
        btadd.setOnClickListener {
            val objectTeman = Teman(null, etAddNama.text.toString(), etAddEmail.text.toString())
            GlobalScope.async {
                val result = mDB?.temanDao()?.insertTeman(objectTeman)
                runOnUiThread {
                    if (result != 0.toLong()){
                        Toast.makeText(this@ProfileTeman, "Sukses menambahkan ${objectTeman.nama}",
                        Toast.LENGTH_SHORT).show()
                        fetchData()
                        etAddNama.setText("")
                        etAddEmail.setText("")
                    } else
                        Toast.makeText(this@ProfileTeman, "Gagal menambahkan ${objectTeman.nama}",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData(){
        GlobalScope.launch {
            val listTeman = mDB?.temanDao()?.getAllTeman()

            runOnUiThread {
                listTeman?.let {
                    val adapter = TemanAdapter(listTeman, this@ProfileTeman)
                    recyclerView?.adapter = adapter

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TemanDatabase.destroyInstance()
        recyclerView?.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)
    }
}

  