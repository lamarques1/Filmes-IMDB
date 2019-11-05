package com.example.filmesimdb.moviedetails

import com.example.filmesimdb.moviedetails.service.MovieDetailsCallback
import com.example.filmesimdb.moviedetails.service.MovieDetailsService

class MovieDetailsPresenter(val view: MovieDetailsContract.View) :
    MovieDetailsContract.Presenter {

    override fun onLoadMovieDetails(imdbID: String, movieDetailsCallback: MovieDetailsCallback) {
        val repository = MovieDetailsService(imdbID).execute().get()
        try {
            if (repository.response){
                movieDetailsCallback.onLoaded(repository)
            }
            else{
                movieDetailsCallback.onError("Não foi possivel carregar o filme!")
            }
        }catch (e : Exception){
            e.printStackTrace()
        }

    }
}