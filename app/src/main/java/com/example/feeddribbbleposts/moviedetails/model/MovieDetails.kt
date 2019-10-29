/* 
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

package com.example.feeddribbbleposts.moviedetails.model

import com.google.gson.annotations.SerializedName

data class MovieDetails (

	@SerializedName("Title") val title : String,
	@SerializedName("Year") val year : String,
	@SerializedName("Rated") val rated : String,
	@SerializedName("Released") val released : String,
	@SerializedName("Runtime") val runtime : String,
	@SerializedName("Genre") val genre : String,
	@SerializedName("Director") val director : String,
	@SerializedName("Writer") val writer : String,
	@SerializedName("Actors") val actors : String,
	@SerializedName("Plot") val plot : String,
	@SerializedName("Language") val language : String,
	@SerializedName("Country") val country : String,
	@SerializedName("Awards") val awards : String,
	@SerializedName("Poster") val poster : String,
	@SerializedName("Ratings") val ratings : List<Ratings>,
	@SerializedName("Metascore") val metascore : Int,
	@SerializedName("imdbRating") val imdbRating : Double,
	@SerializedName("imdbVotes") val imdbVotes : String,
	@SerializedName("imdbID") val imdbID : String,
	@SerializedName("Type") val type : String,
	@SerializedName("DVD") val dVD : String,
	@SerializedName("BoxOffice") val boxOffice : String,
	@SerializedName("Production") val production : String,
	@SerializedName("Website") val website : String,
	@SerializedName("Response") val response : Boolean
)