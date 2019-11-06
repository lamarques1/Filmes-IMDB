package com.example.filmesimdb.movielist

import com.example.filmesimdb.movielist.model.Movie

class MovieListContract {
    interface View {
        fun setPresenter(view : View)
        fun initViews()
        fun initListeners()
        fun displayMovies(movies : List<Movie>)
        fun displayErrorMessage(errorId: Int)
    }
    interface Presenter{
        fun onLoadMovies(title: String)
    }
}