package com.example.feeddribbbleposts.movielist

import android.content.Context
import com.example.feeddribbbleposts.movielist.model.Movie
import com.example.feeddribbbleposts.movielist.service.MovieListCallback

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