package com.example.fund_it

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.hide()
    }
    fun logout(view: View) {
        Firebase.auth.signOut()
        Toast.makeText(this,"Logged out",Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@Dashboard,login::class.java))
        finish()
    }
}