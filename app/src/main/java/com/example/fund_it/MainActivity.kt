package com.example.fund_it

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    fun signup(view: View) {
        val intent=Intent(this@MainActivity,signup::class.java)
        startActivity(intent)
    }

    fun loginn(view: View) {
        val intent=Intent(this@MainActivity,login::class.java)
        startActivity(intent)
    }
}