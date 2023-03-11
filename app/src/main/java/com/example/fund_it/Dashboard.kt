package com.example.fund_it

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.IOException
import java.util.*
import java.util.Collections.list
import kotlin.collections.ArrayList

class Dashboard : baseActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var newAL: ArrayList<lists>
    private lateinit var image: Array<Int>
    private lateinit var name: Array<String>
    private lateinit var dec: Array<String>
    private lateinit var isExpandable: Array<Boolean>
    private lateinit var temparrayList:ArrayList<lists>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.hide()

        image = arrayOf(
            R.drawable.wildlife,
            R.drawable.aquatic,
            R.drawable.birds,
            R.drawable.dogs,
            R.drawable.education,
            R.drawable.eyes,
            R.drawable.old,
            R.drawable.unprev,
            R.drawable.lab,
)

        name = arrayOf(
            "Wildlife",
            "aquatic life",
            "wings",
            "Pet Calendar",
            "education for all",
            "sight life",
            "angels",
            "T-shirt Campaign.",
            "helping hands",)

        dec= arrayOf(
            "Virat Kohli is an Indian international cricketer and " +
                    "former captain of the Indian national team who " +
                    "plays as a right-handed batsman for Royal Challengers" +
                    " Bangalore in the IPL ",

            "Shah Rukh Khan, also known by the initialism SRK, " +
                    "is an Indian actor and film producer who works" +
                    " in Hindi films. Referred to in the media as the" +
                    " \"Baadshah of Bollywood\" and \"King Khan\"",

            "Elon Reeve Musk FRS is a business magnate and investor." +
                    " He is the founder, CEO and chief engineer of SpaceX; " +
                    "angel investor, CEO and product architect of Tesla," +
                    " Inc.; owner and CEO of Twitter, Inc.",

            "Zakir Khan is an Indian comedian and actor. " +
                    "In 2012, he rose to popularity by winning" +
                    " Comedy Central India's Best Stand Up Comedian competition.",

            "Shawn Peter Raul Mendes is a Canadian pop singer-songwriter. " +
                    "He gained a following in 2013, when he posted song covers " +
                    "on the video sharing platform Vine.",

            "A Youtuber and a software engineer at Meta Dublin",

            "Lionel Andrés Messi, also known as Leo Messi, is an Argentine professional" +
                    " footballer who plays as a forward for Ligue 1 club Paris Saint-Germain " +
                    "and captains the Argentina national team.",

            "Cristiano Ronaldo dos Santos Aveiro GOIH ComM is a Portuguese professional " +
                    "footballer who plays as a forward for and captains both Saudi Professional" +
                    " League club Al Nassr and the Portugal national team.",

            "Mahendra Singh Dhoni, commonly known as MS Dhoni, is a former Indian " +
                    "cricketer and captain of the Indian national team in limited-overs " +
                    "formats from 2007 to 2017, and in Test cricket from 2008 to 2014.",

            "Mark Elliot Zuckerberg is an American business magnate, internet entrepreneur," +
                    " and philanthropist. He is known for co-founding the social media website " +
                    "Facebook and its parent company Meta Platforms,",

            "Ratan Naval Tata is an Indian businessman and former chairman of Tata Sons." +
                    " He was also the chairman of the Tata Group from 1990 to 2012, serving " +
                    "also as interim chairman from October 2016 through February 2017")

        var rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)


        newAL = arrayListOf<lists>()
        temparrayList= arrayListOf<lists>()
        getUserdata()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        var rv=findViewById<RecyclerView>(R.id.rv)
//        menuInflater.inflate(R.menu.menu,menu)
//        val item=menu?.findItem(R.id.search_action)
//        val searchView=item?.actionView as SearchView
//
//        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//
//                temparrayList.clear()
//                val searchText=newText!!.toLowerCase(Locale.getDefault())
//                if(searchText.isNotEmpty())
//                {
//                    newAL.forEach {
//                        if(it.name.toLowerCase(Locale.getDefault()).contains(searchText))
//                        {
//                            temparrayList.add(it)
//                        }
//                    }
//                    rv.adapter!!.notifyDataSetChanged()
//                }
//                else
//                {
//                    temparrayList.clear()
//                    temparrayList.addAll(newAL)
//                    rv.adapter!!.notifyDataSetChanged()
//                }
//
//                return false
//            }
//
//        })
//        return super.onCreateOptionsMenu(menu)
//    }

    private fun getUserdata() {
        for (i in image.indices) {
            val Lists = lists(image[i],name[i])
            newAL.add(Lists)
        }

        var rv = findViewById<RecyclerView>(R.id.rv)
        rv.adapter=MyAdapter(newAL)
    }

    fun logout(view: View) {
        Firebase.auth.signOut()
        Toast.makeText(this,"Logged out",Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@Dashboard,login::class.java))
        finish()
    }
}