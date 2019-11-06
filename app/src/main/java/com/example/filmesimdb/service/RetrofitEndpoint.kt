package com.example.filmesimdb.service

import com.example.filmesimdb.moviedetails.model.MovieDetails
import com.example.filmesimdb.movielist.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitEndpoint {
    @GET("./?apikey=e2a2df13")
    fun list(@Query("s") title: String): Call<MovieList>

    @GET("./?apikey=e2a2df13")
    fun details(@Query("i") imdbId: String): Call<MovieDetails>
}