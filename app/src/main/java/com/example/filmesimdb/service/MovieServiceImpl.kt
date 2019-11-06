package com.example.filmesimdb.service

import com.example.filmesimdb.R
import com.example.filmesimdb.moviedetails.model.MovieDetails
import com.example.filmesimdb.movielist.model.Movie
import com.example.filmesimdb.movielist.model.MovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MovieServiceImpl : MovieServiceApi {

    val mRetrofit = RetrofitConfig().getClient().create(RetrofitEndpoint::class.java)

    /**
     * Busca uma lista de filmes
     * @param title - Filtro da busca
     */
    override fun getMovieList(
        title: String,
        callback: MovieServiceApi.MovieCallback<List<Movie>>
    ) {
        val callMovies = mRetrofit.list(title.trim())
        callMovies.enqueue(object: Callback<MovieList>{
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                try {
                    // recebe o json da resposta
                    val result = response.body()
                    //Filtra o resultado para retornar apenas os filmes
                    val movieList = result!!.movies.filter { it.type == "movie" }

                    if (movieList.isNotEmpty()){
                        callback.onLoaded(movieList)
                    }else{
                        callback.onError(R.string.erro_filme_nao_encontrado)
                    }
                }catch (e : Exception){
                    callback.onError(R.string.erro_filme_nao_encontrado)
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                callback.onError(R.string.erro_carregar_filmes)
            }
        })
    }

    /**
     * Busca os detalhes de um filme específico
     * @param imdbId - Id do filme
     */
    override fun getMovieDetails(
        imdbId: String,
        callback: MovieServiceApi.MovieCallback<MovieDetails>
    ) {
        val callDetails = mRetrofit.details(imdbId)
        callDetails.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                if (response.code() == 200){
                    val movieDetails = response.body()
                    callback.onLoaded(movieDetails!!)
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                callback.onError(R.string.erro_carregar_detalhes)
            }
        })
    }

}