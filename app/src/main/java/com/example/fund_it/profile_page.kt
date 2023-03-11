package com.example.fund_it

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.fund_it.Constants.PICK_IMAGE_REQUEST_CODE
import com.example.fund_it.Constants.READ_STORAGE_PERMISSION_CODE
import java.io.IOException

class profile_page : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    companion object {
        private const val READ_STORAGE_PERMISSION_CODE = 1
        private const val PICK_IMAGE_REQUEST_CODE = 2
    }

    private var mselectedImageFileUri: Uri? = null
    private var mProfileImageURL: String = ""
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
        supportActionBar?.hide()
        val iv_profile_user_image = findViewById<ImageView>(R.id.img)
        iv_profile_user_image.setOnClickListener {
            Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            ) {
                showImageChooser(this)
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_STORAGE_PERMISSION_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showImageChooser(this)

            } else {
                Toast.makeText(
                    this, "You Denied",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val iv_profile_user_image = findViewById<ImageView>(R.id.img)
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST_CODE) {
                if (data != null) {
                    try {
                        mselectedImageFileUri = data.data!!
                        Glide
                            .with(this)
                            .load(mselectedImageFileUri)
                            .centerCrop()
                            .placeholder(R.drawable.old)
                            .into(iv_profile_user_image)
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    fun submit(view: View) {
        if (validateDetails())
        {
            val name = findViewById<EditText>(R.id.name)
            val email = findViewById<EditText>(R.id.email)
            val contact = findViewById<EditText>(R.id.mobile)
            val address=findViewById<EditText>(R.id.address)
            val male=findViewById<RadioButton>(R.id.m)
            val female=findViewById<RadioButton>(R.id.f)
            val img=findViewById<ImageView>(R.id.img)

            val userHashMap=HashMap<String,Any>()


            val username=name.text.toString().trim{it <=' '}
            val emailid=email.text.toString().trim{it <=' '}
            val moblieno=contact.text.toString().trim{it <=' '}
            val addresss=address.text.toString().trim{it <=' '}


            val gender=if(male.isChecked)
            {
                Constants.MALE
            } else {
                Constants.FEMALE
            }
            if (moblieno.isNotEmpty())
            {
                userHashMap[Constants.MOBILE]=moblieno.toLong()
            }

            if (addresss.isNotEmpty())
            {
                userHashMap[Constants.ADDRESS]=addresss
            }
            if (emailid.isNotEmpty())
            {
                userHashMap[Constants.EMAIL]=email
            }
            if (username.isNotEmpty())
            {
                userHashMap[Constants.USERNAME]=username
            }
            userHashMap[Constants.GENDER]=gender
            startActivity(Intent(this@profile_page,Dashboard::class.java))
            Toast.makeText(this,"Profile updated",Toast.LENGTH_SHORT).show()
            finish()
        }
    }


    fun showImageChooser(activity: Activity) {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    private fun validateDetails(): Boolean {
        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val contact = findViewById<EditText>(R.id.mobile)
        val address=findViewById<EditText>(R.id.address)
        val gender=findViewById<RadioGroup>(R.id.gender)
        val male=findViewById<RadioButton>(R.id.m)
        val female=findViewById<RadioButton>(R.id.f)
        val img=findViewById<ImageView>(R.id.img)

        return when {
            TextUtils.isEmpty(email.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please complete the profile", Toast.LENGTH_SHORT).show()
                false

            }
            TextUtils.isEmpty(name.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please complete the profile", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(contact.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please complete the profile", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(address.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please complete the profile", Toast.LENGTH_SHORT).show()
                false
            }

            !male.isChecked and !female.isChecked-> {
                Toast.makeText(this, "Please complete the profile", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                true
            }
        }
    }
}
