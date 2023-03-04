package com.example.fund_it

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.DropBoxManager.Entry
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.fund_it.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class login : baseActivity() {
    lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        supportActionBar?.hide()
    }

    fun signup(view: View) {
        val intent=Intent(this@login,signup::class.java)
        startActivity(intent)
    }

    fun loginb(view: View) {
        loggingin()
    }

    fun loggingin() {
        if (validatedetails()) {
            showPB()

            val email: String = binding.email.text.toString().trim { it <= ' ' }
            val password: String = binding.password.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        hidePB()
                        Toast.makeText(this,"Logging in!!",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@login,Dashboard::class.java))
                    } else {
                        hidePB()
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }

    fun validatedetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter your email id", true)
                false
            }

            TextUtils.isEmpty(binding.password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter your password", true)
                false
            }
            else -> {
                true
            }
        }
    }

    fun forgot_password(view: View) {
        startActivity(Intent(this@login,forgot_password::class.java))
    }

    fun back(view: View) {
        startActivity(Intent(this@login,MainActivity::class.java))
    }
}