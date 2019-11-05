package com.example.filmesimdb.movielist.service

import com.example.filmesimdb.movielist.model.Movie

interface MovieListCallback {
    fun onLoaded(result : List<Movie>)
    fun onError(error : String)
}