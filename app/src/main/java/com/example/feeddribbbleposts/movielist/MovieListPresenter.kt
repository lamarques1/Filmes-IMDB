package com.example.feeddribbbleposts.movielist

import com.example.feeddribbbleposts.movielist.service.MovieListService
import java.lang.Exception

class MovieListPresenter(val view : MovieListContract.View) : MovieListContract.Presenter {

    override fun onLoadMovies(title: String) {

        try {
            val retorno = MovieListService(title).execute().get()
            if (retorno != null) {
                if (retorno.response){
                    view.displayMovies(retorno.movies)
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}