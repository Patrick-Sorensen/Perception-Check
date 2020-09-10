package com.example.perceptioncheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = Firebase.auth

        login_button_login.setOnClickListener {
            performLogin()
        }

        back_to_register_textview_login.setOnClickListener {
            Log.d("LoginActivity", "Try to show main activity")

            // launch main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performLogin() {
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        if(email.isEmpty() || password.isEmpty() || password.length < 6) {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            return
        }

        login_progressbar_login.visibility = View.VISIBLE

        Log.d("LoginActivity", "Email is: " + email)
        Log.d("LoginActivity", "Password: $password")

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    Log.d("LoginActivity", "Successfully created user")

                    // Launch login activity
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.d("LoginActivity", "Failed to create a user")
                    return@addOnCompleteListener
                }
            }
            .addOnFailureListener {
                Log.d("LoginActivity", "Failed to create user: ${it.message}")
            }
    }

}