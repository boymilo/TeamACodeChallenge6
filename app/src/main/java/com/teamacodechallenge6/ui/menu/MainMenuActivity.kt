package com.teamacodechallenge6.ui.menu

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.teamacodechallenge6.R
import com.teamacodechallenge6.data.local.SharedPref
import com.teamacodechallenge6.playGame.MainGameComputer
import com.teamacodechallenge6.playGame.MainGamePlayer
import com.teamacodechallenge6.ui.login.LoginPresenter
import com.teamacodechallenge6.ui.pilihLawan.PilihLawan
import com.teamacodechallenge6.ui.profileteman.ProfileTeman
import kotlinx.android.synthetic.main.activity_main.*

class MainMenuActivity : AppCompatActivity(), MainMenuView {
    private lateinit var presenter: MainMenuPresenter
    val parent: ConstraintLayout by lazy { findViewById(R.id.main) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainMenuPresenterImp(this)
        presenter.showUsername()
        ivMenu1.setOnClickListener {
            startActivity(Intent(this, PilihLawan::class.java))
            finish()
        }
        ivMenu2.setOnClickListener{
            startActivity(Intent(this, MainGameComputer::class.java))
            finish()
        }
        ivMenu3.setOnClickListener{
            startActivity(Intent(this, ProfileTeman::class.java))
            finish()
        }
    }

    override fun onSuccess(msg: String) {
        val snackbar = Snackbar.make(parent, msg, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Tutup"){
            snackbar.dismiss()
        }.show()
    }

    override fun onBackPressed() {
        finish()
    }
}