package com.example.fund_it

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
    }

    fun signup(view: View) {
        val intent=Intent(this@login,signup::class.java)
        startActivity(intent)
    }

    fun loginb(view: View) {
        Toast.makeText(this,"App is in progress", Toast.LENGTH_SHORT).show()
    }
}