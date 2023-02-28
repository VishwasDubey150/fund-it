package com.example.fund_it

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.fund_it.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signup : baseActivity() {
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
    }

    fun login(view: View) {
        val intent = Intent(this@signup, login::class.java)
        startActivity(intent)
    }

    fun back(view: View) {
        val intent = Intent(this@signup, MainActivity::class.java)
        startActivity(intent)
    }

    fun skip(view: View) {

    }

    fun create(view: View) {
        registerUser()
    }

    fun validatedetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.fname.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter your first name", true)
                false
            }

            TextUtils.isEmpty(binding.lname.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter your last name", true)
                false
            }

            TextUtils.isEmpty(binding.email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter your email id", true)
                false
            }

            TextUtils.isEmpty(binding.password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter your password", true)
                false
            }

            TextUtils.isEmpty(binding.contact.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter your Contact number", true)
                false
            }
            else -> {
                true
            }
        }
    }

    private fun registerUser() {

        if (validatedetails()) {
            showPB()

            val email: String = binding.email.text.toString().trim { it <= ' ' }
            val password: String = binding.password.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        hidePB()
                        Toast.makeText(this,"Account is successfully created!!",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@signup,login::class.java))

                    } else {
                        hidePB()
                        Toast.makeText(this,"Something went wrong!!",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}