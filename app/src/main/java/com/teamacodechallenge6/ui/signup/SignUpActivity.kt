package com.teamacodechallenge6.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.teamacodechallenge6.R
import com.teamacodechallenge6.ui.login.LoginActivity
import com.teamacodechallenge6.ui.login.LoginPresenter

class SignUpActivity : AppCompatActivity(), SignUpView {
    private var presenter: SignUpPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val btnSignUp by lazy { this.findViewById<Button>(R.id.btnSignUp) }
        val logo by lazy { this.findViewById<ImageView>(R.id.ivLogo) }
        val username by lazy {this.findViewById<EditText>(R.id.etUsername)}
        val password by lazy {this.findViewById<EditText>(R.id.etPassword)}
        val rePassword by lazy {this.findViewById<EditText>(R.id.etRePassword)}
        val email by lazy {this.findViewById<EditText>(R.id.etEmail)}
        presenter = SignUpPresenterImp(this)

        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(logo)
        btnSignUp.setOnClickListener {
            if (password.text.toString() == rePassword.text.toString()){
                presenter?.signUp(username.text.toString(), password.text.toString(), email.text.toString())
                var username1 = username.text.toString()
                var password1 = password.text.toString()
                var email1 = email.text.toString()

                Toast.makeText(this, "User berhasil didaftarkan", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onSuccess() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}