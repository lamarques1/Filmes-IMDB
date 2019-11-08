package com.example.filmesimdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.tabs.TabLayout

class Main2Activity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var textView: TextView
    private val searchTypes = listOf("Movie", "Series", "Game")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        textView = findViewById(R.id.textView)
        tabLayout = findViewById(R.id.tabLayout)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
                return
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                return
            }

            override fun onTabSelected(p0: TabLayout.Tab) {
                Log.d("Teste", p0.position.toString())
                textView.text = searchTypes[p0.position]
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.app_bar_search -> {
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

}
