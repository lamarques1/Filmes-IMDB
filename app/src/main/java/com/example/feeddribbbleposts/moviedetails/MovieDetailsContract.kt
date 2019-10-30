package com.example.feeddribbbleposts.moviedetails

import com.example.feeddribbbleposts.moviedetails.model.MovieDetails

class MovieDetailsContract {
    interface View{
        fun initViews()
        fun displayMovieDetails(movie : MovieDetails)
        fun setPresenter()
    }
    interface Presenter{
        fun onLoadMovieDetails(imdbID : String)
    }
}