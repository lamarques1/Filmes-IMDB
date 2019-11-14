package com.example.filmesimdb.moviedetails

import com.example.filmesimdb.moviedetails.model.MovieDetails
import com.example.filmesimdb.moviedetails.model.Season

class MovieDetailsContract {
    interface View{
        fun initViews()
        fun displayMovieDetails(movie : MovieDetails)
        fun displaySeasonInfo(season: Season)
        fun displayErrorMessage(errorId : Int)
        fun showTabs(show : Boolean)
        fun setPresenter()
        fun initListeners()
    }
    interface Presenter{
        fun onLoadMovieDetails(imdbID : String)
        fun onLoadSeasonInfo(imdbID: String, season: String)
    }
}