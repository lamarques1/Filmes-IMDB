package com.example.filmesimdb.service

import com.example.filmesimdb.moviedetails.model.Episodes
import com.example.filmesimdb.moviedetails.model.MovieDetails
import com.example.filmesimdb.moviedetails.model.Season
import com.example.filmesimdb.movielist.model.Movie
import retrofit2.http.GET

interface MovieServiceApi {
    interface MovieCallback<T>{
        fun onLoaded(result: T,totalItems: Int)
        fun onError(errorId: Int)
    }
    interface MovieDetailsCallback<T>{
        fun onLoaded(result: T)
        fun onError(errorId: Int)
    }

    @GET("./?apikey=e2a2df13")
    fun getMovieList(title : String, page : String, type : String, callback : MovieCallback<List<Movie>>)

    @GET("./?apikey=e2a2df13")
    fun getMovieDetails(imdbId : String, callback : MovieDetailsCallback<MovieDetails>)

    @GET("./?apikey=e2a2df13")
    fun getSeason(imdbId : String, season : String, callback: MovieDetailsCallback<Season>)
}