package com.example.fund_it

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.fund_it.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class forgot_password : baseActivity() {
    lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.submit.setOnClickListener {
            resetpassword()
        }
    }
    fun back(view: View) {
        startActivity(Intent(this@forgot_password, login::class.java))
    }

    fun resetpassword()
    {
        val email: String = binding.email.text.toString().trim { it <= ' ' }
        if(email.isEmpty())
        {
            Toast.makeText(this,"Please enter email address", Toast.LENGTH_SHORT).show()
        }
        else
        {
            showPB()
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener{
                        task ->
                    if (task.isSuccessful)
                    {
                        hidePB()
                        Toast.makeText(this,"Email sent to reset Password", Toast.LENGTH_LONG).show()
                        finish()
                    }
                    else
                    {
                        hidePB()
                        Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}