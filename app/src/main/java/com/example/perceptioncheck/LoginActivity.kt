package com.example.perceptioncheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()

            login_progressbar_login.visibility = View.VISIBLE

            Log.d("LoginActivity", "Email is: " + email)
            Log.d("LoginActivity", "Password: $password")
        }

        back_to_register_textview_login.setOnClickListener {
            Log.d("LoginActivity", "Try to show main activity")

            // launch main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}