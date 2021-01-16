package com.teamacodechallenge6.ui.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.teamacodechallenge6.R
import com.teamacodechallenge6.ui.playGame.MainGameComputer
import com.teamacodechallenge6.ui.login.LoginActivity
import com.teamacodechallenge6.ui.pilihLawan.PilihLawan
import com.teamacodechallenge6.ui.profileteman.ProfileTeman
import kotlinx.android.synthetic.main.activity_main.*

class MainMenuActivity : AppCompatActivity(), MainMenuView {
    private lateinit var presenter: MainMenuPresenter
    private val username= mutableListOf<String>()
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
        btLogout.setOnClickListener {
            presenter.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onSuccess(msg: String) {
        val snackbar = Snackbar.make(parent,"Selamat datang $msg", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Tutup"){
            snackbar.dismiss()
        }.show()
        username.add(msg)
        tvPlayer.text=msg
        tvPlayer2.text=msg
    }

    override fun onBackPressed() {
        finish()
    }
}