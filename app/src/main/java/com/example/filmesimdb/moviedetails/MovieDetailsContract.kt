package com.example.feeddribbbleposts.moviedetails

import com.example.feeddribbbleposts.moviedetails.model.MovieDetails
import com.example.feeddribbbleposts.moviedetails.service.MovieDetailsCallback

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