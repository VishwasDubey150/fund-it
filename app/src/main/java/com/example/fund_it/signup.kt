package com.example.fund_it

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()
    }
    fun login(view: View) {
        val intent=Intent(this@signup,login::class.java)
        startActivity(intent)
    }

    fun back(view: View) {
        val intent=Intent(this@signup,MainActivity::class.java)
        startActivity(intent)
    }

    fun skip(view: View) {}
    fun create(view: View) {
        Toast.makeText(this,"App is in progress",Toast.LENGTH_SHORT).show()
    }
}