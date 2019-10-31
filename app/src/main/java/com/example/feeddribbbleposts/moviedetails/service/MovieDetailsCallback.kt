package com.example.feeddribbbleposts.moviedetails.service

import com.example.feeddribbbleposts.moviedetails.model.MovieDetails

interface MovieDetailsCallback {
    fun onLoaded(result : MovieDetails)
    fun onError(error : String)
}