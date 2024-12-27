package com.example.radiate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SigninPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_page)

        val btnForgetPassword = findViewById<TextView>(R.id.txtForgetPassword)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)

        btnForgetPassword.setOnClickListener {
            startActivity(Intent(this,ForgetPassword::class.java))
        }
        btnSignIn.setOnClickListener {
            startActivity(Intent(this,HomePage::class.java))
        }

    }
}