package com.example.radiate

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SigninPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_page)

        val btnforgetPassword = findViewById<TextView>(R.id.txtForgetPassword)


        btnforgetPassword.setOnClickListener {
            startActivity(Intent(this,ForgetPassword::class.java))
        }

    }
}