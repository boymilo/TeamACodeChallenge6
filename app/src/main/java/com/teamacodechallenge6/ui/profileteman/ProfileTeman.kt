package com.teamacodechallenge6.ui.profileteman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.teamacodechallenge6.App.Companion.mDB
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamacodechallenge6.R
import com.teamacodechallenge6.database.TemanDatabase
import com.teamacodechallenge6.ui.pilihLawan.PilihLawan
import kotlinx.android.synthetic.main.activity_profile_teman.*
import kotlinx.android.synthetic.main.addfriend_dialog.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileTeman : AppCompatActivity(), TemanView {
    private var presenter: TemanPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_teman)
        presenter = TemanPresenterImp(this)

        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        onResume()
        btadd.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.addfriend_dialog, null, false)
            val dialogBuilder = AlertDialog.Builder(this).setView(view)
            val dialogD1 = dialogBuilder.create()
            dialogD1.setCancelable(false)
            view.btSaveFriend.setOnClickListener {
                val namaTeman = view.etNama.text.toString()
                val emailTeman = view.etEmail.text.toString()
                if (namaTeman != "" && emailTeman != "") {
                    presenter?.addTeman(namaTeman,emailTeman)
                    onSuccess(namaTeman)
                    dialogD1.dismiss()
                } else{
                    onFailed()
                }
            }
            view.btClose.setOnClickListener{
                dialogD1.dismiss()
            }
            dialogD1.show()
        }

        ib_home.setOnClickListener {
            startActivity(Intent(this, PilihLawan::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData() {
        /*GlobalScope.launch {
            val listTeman = mDB?.temanDao()?.getAllTeman()
            runOnUiThread {
                listTeman?.let {
                    val adapter = TemanAdapter(listTeman, this@ProfileTeman)
                    recyclerView.adapter = adapter
                }
            }
        }*/
        presenter?.listTeman(recyclerView, this@ProfileTeman)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.DestroyDB()
    }
    override fun onSuccess(msg: String) {
        Toast.makeText(
            this@ProfileTeman, "Username berhasil ditambahkan",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onFailed() {
        Toast.makeText(
            this@ProfileTeman, "Password dan Username harus diisi",
            Toast.LENGTH_SHORT
        ).show()
    }

}