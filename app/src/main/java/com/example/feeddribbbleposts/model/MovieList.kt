package com.example.feeddribbbleposts.model
import com.google.gson.annotations.SerializedName


data class MovieList (

	@SerializedName("Search") val movies : List<Movie>,
	@SerializedName("totalResults") val totalResults : Int,
	@SerializedName("Response") val response : Boolean
)