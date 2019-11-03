package com.example.feeddribbbleposts.moviedetails

import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feeddribbbleposts.R
import com.example.feeddribbbleposts.moviedetails.adapter.MovieDetailsAdapter
import com.example.feeddribbbleposts.moviedetails.model.MovieDetails
import com.example.feeddribbbleposts.moviedetails.service.MovieDetailsCallback
import com.example.feeddribbbleposts.moviedetails.service.MovieDetailsService
import com.squareup.picasso.Picasso

class MovieDetailsView : AppCompatActivity(), MovieDetailsContract.View {

    private lateinit var imdbID : String
    private lateinit var retorno : MovieDetails
    private lateinit var txtTitle : TextView
    private lateinit var txtYear : TextView
    private lateinit var txtRunTime : TextView
    private lateinit var ratingBar : RatingBar
    private lateinit var txtGenre : TextView
    private lateinit var txtPlot : TextView
    private lateinit var txtDirector : TextView
    private lateinit var txtWriter : TextView
    private lateinit var txtActors : TextView
    private lateinit var imgPoster : ImageView

    private lateinit var adapter : MovieDetailsAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var presenter : MovieDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        supportActionBar!!.hide()

        imdbID = intent.getStringExtra("imdbID")!!

        retorno = MovieDetailsService(imdbID).execute().get()

        setPresenter()
        initViews()

        presenter.onLoadMovieDetails(imdbID, object : MovieDetailsCallback{
            override fun onLoaded(result: MovieDetails) {
                displayMovieDetails(result)
            }

            override fun onError(error: String) {
                displayErrorMessage(error)
            }

        })
    }

    override fun setPresenter() {
        presenter = MovieDetailsPresenter(this)
    }

    override fun initViews() {
        txtTitle = findViewById(R.id.txtTitle)
        txtYear = findViewById(R.id.txtYear)
        txtRunTime = findViewById(R.id.txtRunTime)
        ratingBar = findViewById(R.id.ratingBar)
        txtGenre = findViewById(R.id.txtGenre)
        txtPlot = findViewById(R.id.txtPlot)
        txtDirector = findViewById(R.id.txtDirector)
        txtWriter = findViewById(R.id.txtWriter)
        txtActors = findViewById(R.id.txtActors)
        imgPoster = findViewById(R.id.imgPoster)
        recyclerView = findViewById(R.id.recyclerViewRatings)
    }

    override fun displayMovieDetails(movie : MovieDetails) {
        txtTitle.text = movie.title
        txtYear.text = movie.year
        txtRunTime.text = movie.runtime
        ratingBar.rating = (movie.imdbRating/2).toFloat()
        txtGenre.text = movie.genre
        txtPlot.text = movie.plot
        txtDirector.text = movie.director
        txtWriter.text = movie.writer
        txtActors.text = movie.actors

        val posterUri = Uri.parse(movie.poster)
        val colorDrawable = ColorDrawable(0xFFFFFF)
        Picasso.get()
            .load(posterUri)
            .fit().centerCrop()
            .placeholder(colorDrawable)
            .into(imgPoster)

        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieDetailsAdapter(applicationContext, movie.ratings)
        recyclerView.adapter = adapter
    }

    override fun displayErrorMessage(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
