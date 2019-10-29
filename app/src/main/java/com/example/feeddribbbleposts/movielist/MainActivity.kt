package com.example.feeddribbbleposts.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feeddribbbleposts.R
import com.example.feeddribbbleposts.movielist.adapter.MovieListAdapter
import com.example.feeddribbbleposts.movielist.service.MovieListService
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var etTitle : EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSearch : Button
    private lateinit var adapter : MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initListeners()
    }
    private fun initViews() {
        etTitle = findViewById(R.id.etTitle)
        btnSearch = findViewById(R.id.btnSearch)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initListeners() {
        btnSearch.setOnClickListener {

            try {
                val retorno = MovieListService(etTitle.text.toString())
                    .execute().get()
                if (retorno != null) {
                    recyclerView.visibility = View.VISIBLE
                    adapter = MovieListAdapter(applicationContext, retorno.movies)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }


        }
    }

}

