package com.example.filmesimdb.moviedetails

import com.example.filmesimdb.moviedetails.model.MovieDetails
import com.example.filmesimdb.moviedetails.service.MovieDetailsCallback

class MovieDetailsContract {
    interface View{
        fun initViews()
        fun displayMovieDetails(movie : MovieDetails)
        fun displayErrorMessage(error : String)
        fun setPresenter()
    }
    interface Presenter{
        fun onLoadMovieDetails(imdbID : String, movieDetailsCallback: MovieDetailsCallback)
    }
}