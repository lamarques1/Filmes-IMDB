package com.example.feeddribbbleposts.movielist

import com.example.feeddribbbleposts.movielist.model.Movie

class MovieListContract {
    interface View {
        fun displayMovies(movies: List<Movie> )
        fun initViews()
        fun initListeners()
        fun setPresenter(view : View)
    }
    interface Presenter{
        fun onLoadMovies(title: String)
    }
}