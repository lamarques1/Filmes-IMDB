package com.example.feeddribbbleposts.movielist.service

import com.example.feeddribbbleposts.movielist.model.Movie

interface MovieListCallback {
    fun onLoaded(result : List<Movie>)
    fun onError(error : String)
}