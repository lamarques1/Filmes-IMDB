package com.example.filmesimdb.movielist

import android.content.Context
import com.example.filmesimdb.movielist.model.Movie
import com.example.filmesimdb.movielist.service.MovieListCallback

class MovieListContract {
    interface View {
        fun setPresenter(view : View)
        fun initViews()
        fun initListeners()
        fun displayMovies(movies : List<Movie>)
        fun displayErrorMessage(error: String)
    }
    interface Presenter{
        fun onLoadMovies(title: String, movieListCallback: MovieListCallback)
    }
}