package com.example.feeddribbbleposts.movielist

import com.example.feeddribbbleposts.movielist.service.MovieListCallback
import com.example.feeddribbbleposts.movielist.service.MovieListService
import java.lang.Exception

class MovieListPresenter(val view : MovieListContract.View) : MovieListContract.Presenter {

    override fun onLoadMovies(title: String, movieListCallback: MovieListCallback) {
        try {
            val repository = MovieListService(title).execute().get()

            if (repository.response){
                movieListCallback.onLoaded(repository.movies)
            }
            else{
                movieListCallback.onError("Filme não encontrado!")
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}