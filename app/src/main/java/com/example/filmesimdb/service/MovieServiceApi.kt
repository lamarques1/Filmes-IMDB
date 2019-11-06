package com.example.filmesimdb.service

import com.example.filmesimdb.moviedetails.model.MovieDetails
import com.example.filmesimdb.movielist.model.MovieList
import retrofit2.http.GET

interface MovieServiceApi {
    interface MovieCallback<T>{
        fun onLoaded(result: T)
        fun onError(errorId: Int)
    }

    @GET("./?apikey=e2a2df13")
    fun getMovieList(title: String, callback : MovieCallback<MovieList>)

    @GET("./?apikey=e2a2df13")
    fun getMovieDetails(imdbId: String, callback : MovieCallback<MovieDetails>)
}