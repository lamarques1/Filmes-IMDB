package com.example.filmesimdb.moviedetails

import com.example.filmesimdb.moviedetails.adapter.EpisodeListAdapter
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
        fun getEpisodeAdapter() : EpisodeListAdapter
        fun initListeners()
    }
    interface Presenter{
        fun onLoadMovieDetails(imdbId : String)
        fun onLoadSeasonInfo(imdbId: String, season: String)
        fun onUpdateEpisodeList(imdbId: String, season: String)
    }
}