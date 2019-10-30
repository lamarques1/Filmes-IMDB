package com.example.feeddribbbleposts.moviedetails

import com.example.feeddribbbleposts.moviedetails.service.MovieDetailsService

class MovieDetailsPresenter(val view: MovieDetailsContract.View) : MovieDetailsContract.Presenter {
    override fun onLoadMovieDetails(imdbID: String) {
        val retorno = MovieDetailsService(imdbID).execute().get()
        try {
            if (retorno != null){
                if (retorno.response){
                    view.displayMovieDetails(retorno)
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }

    }
}