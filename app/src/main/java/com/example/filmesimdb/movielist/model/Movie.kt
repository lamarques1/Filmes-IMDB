package com.example.filmesimdb.movielist.model
import com.google.gson.annotations.SerializedName

data class Movie (

    @SerializedName("Title") val title : String,
    @SerializedName("Year") val year : String,
    @SerializedName("imdbID") val imdbID : String,
    @SerializedName("Type") var type : String,
    @SerializedName("Poster") val poster : String
)