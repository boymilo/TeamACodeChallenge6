package com.teamacodechallenge6.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.teamacodechallenge6.R
import com.teamacodechallenge6.ui.login.LoginPresenter

class MainMenuActivity : AppCompatActivity(), MainMenuView {
    private var presenter: MainMenuPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSuccess(msg: String) {
        setContentView(R.layout.activity_main)
        val parent by lazy { findViewById<ConstraintLayout>(R.id.main) }
        val snackbar = Snackbar.make(parent, "Selamat datang", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Tutup"){
            snackbar.dismiss()
        }.show()
    }
}