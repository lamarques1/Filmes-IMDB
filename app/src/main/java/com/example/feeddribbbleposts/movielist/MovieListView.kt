package com.example.feeddribbbleposts.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feeddribbbleposts.R
import com.example.feeddribbbleposts.movielist.adapter.MovieListAdapter
import com.example.feeddribbbleposts.movielist.model.Movie

class MovieListView : AppCompatActivity(), MovieListContract.View {


    private lateinit var etTitle : EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSearch : Button
    private lateinit var adapter : MovieListAdapter
    private lateinit var presenter : MovieListPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        supportActionBar!!.hide()

        setPresenter(this)
        initViews()
        initListeners()
    }

    override fun setPresenter(view: MovieListContract.View) {
        presenter = MovieListPresenter(view)
    }

    override fun initViews() {
        etTitle = findViewById(R.id.etTitle)
        btnSearch = findViewById(R.id.btnSearch)
        recyclerView = findViewById(R.id.recyclerViewMovies)

    }

     override fun initListeners() {
        btnSearch.setOnClickListener {
            presenter.onLoadMovies(etTitle.text.toString())
        }
    }
    override fun displayMovies(movies: List<Movie>) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieListAdapter(applicationContext, movies)
        recyclerView.adapter = adapter
    }
}

