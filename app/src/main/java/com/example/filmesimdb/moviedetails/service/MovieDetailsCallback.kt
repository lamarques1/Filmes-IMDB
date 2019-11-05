package com.example.filmesimdb.moviedetails.service

import com.example.filmesimdb.moviedetails.model.MovieDetails

interface MovieDetailsCallback {
    fun onLoaded(result : MovieDetails)
    fun onError(error : String)
}