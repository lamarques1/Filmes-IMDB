package com.example.filmesimdb.moviedetails

import com.example.filmesimdb.moviedetails.model.MovieDetails

class MovieDetailsContract {
    interface View{
        fun initViews()
        fun displayMovieDetails(movie : MovieDetails)
        fun displayErrorMessage(errorId : Int)
        fun setPresenter()
        fun initListeners()
    }
    interface Presenter{
        fun onLoadMovieDetails(imdbID : String)
    }
}