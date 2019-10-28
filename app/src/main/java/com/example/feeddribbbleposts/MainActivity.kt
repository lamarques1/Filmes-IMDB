package com.example.feeddribbbleposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feeddribbbleposts.adapter.MovieAdapter
import com.example.feeddribbbleposts.service.MovieListService

class MainActivity : AppCompatActivity() {

    private lateinit var etTitle : EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSearch : Button
    private lateinit var adapter : MovieAdapter

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
            val retorno = MovieListService(etTitle.text.toString()).execute().get()

            recyclerView.visibility = View.VISIBLE
            adapter = MovieAdapter(applicationContext, retorno.movies)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)


        }
    }

}

