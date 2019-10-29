package com.example.feeddribbbleposts.moviedetails

import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.feeddribbbleposts.R
import com.example.feeddribbbleposts.moviedetails.model.MovieDetails
import com.example.feeddribbbleposts.moviedetails.service.MovieDetailsService
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var imdbID : String
    private lateinit var retorno : MovieDetails
    private lateinit var txtTitle : TextView
    private lateinit var txtYear : TextView
    private lateinit var txtRunTime : TextView
    private lateinit var txtGenre : TextView
    private lateinit var txtPlot : TextView
    private lateinit var txtDirector : TextView
    private lateinit var txtWriter : TextView
    private lateinit var txtActors : TextView
    private lateinit var imgPoster : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        imdbID = intent.getStringExtra("imdbID")

        retorno = MovieDetailsService(imdbID).execute().get()

        initView()
        loadData()
    }

    private fun initView(){
        txtTitle = findViewById(R.id.txtTitle)
        txtYear = findViewById(R.id.txtYear)
        txtRunTime = findViewById(R.id.txtRunTime)
        txtGenre = findViewById(R.id.txtGenre)
        txtPlot = findViewById(R.id.txtPlot)
        txtDirector = findViewById(R.id.txtDirector)
        txtWriter = findViewById(R.id.txtWriter)
        txtActors = findViewById(R.id.txtActors)
        imgPoster = findViewById(R.id.imgPoster)
    }

    private fun loadData(){
        txtTitle.text = retorno.title
        txtYear.text = retorno.year
        txtRunTime.text = retorno.runtime
        txtGenre.text = retorno.genre
        txtPlot.text = retorno.plot
        txtDirector.text = retorno.director
        txtWriter.text = retorno.writer
        txtActors.text = retorno.actors

        val posterUri = Uri.parse(retorno.poster)
        val colorDrawable = ColorDrawable(0xFFFFFF)
        Picasso.get()
            .load(posterUri)
            .fit().centerCrop()
            .placeholder(colorDrawable)
            .into(imgPoster)
    }
}
