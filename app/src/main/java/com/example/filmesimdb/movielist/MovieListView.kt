package com.example.filmesimdb.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmesimdb.R
import com.example.filmesimdb.movielist.adapter.MovieListAdapter
import com.example.filmesimdb.movielist.model.Movie

class MovieListView : AppCompatActivity(), MovieListContract.View {

    private lateinit var etTitle : EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSearch : ImageView
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

    /**
     * Ao pesquisar, carrega o resultado por meio do presenter
     */
    override fun initListeners() {
        btnSearch.setOnClickListener {
            presenter.onLoadMovies(etTitle.text.toString())
        }
    }

    /**
     * Exibe a lista de filmes recebida do presenter
     */
    override fun displayMovies(movies: List<Movie>) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            MovieListAdapter(applicationContext, movies)
        recyclerView.adapter = adapter
    }

    /**
     * Caso a busca falhe, exibe uma mensagem de erro
     * @param errorId - Id da string de erro para exibição
     */
    override fun displayErrorMessage(errorId: Int) {
        Toast.makeText(this, errorId, Toast.LENGTH_SHORT).show()
    }
}

