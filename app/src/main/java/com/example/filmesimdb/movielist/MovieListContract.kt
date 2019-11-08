package com.example.filmesimdb.movielist

import com.example.filmesimdb.movielist.adapter.MovieListAdapter
import com.example.filmesimdb.movielist.model.Movie

class MovieListContract {
    interface View {
        fun getAdapter() : MovieListAdapter
        fun setPresenter(view : View)
        fun initViews()
        fun initListeners()
        fun displayMovies(movies : List<Movie>)
        fun displayErrorMessage(errorId: Int)
    }
    interface Presenter{
        fun onLoadMovies(title: String, type: String)
    }
}