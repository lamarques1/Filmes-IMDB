package com.example.filmesimdb.moviedetails

import com.example.filmesimdb.moviedetails.model.MovieDetails
import com.example.filmesimdb.service.MovieServiceApi
import com.example.filmesimdb.service.MovieServiceImpl


class MovieDetailsPresenter(val view: MovieDetailsContract.View) :
    MovieDetailsContract.Presenter {

    /**
     * Busca os detalhes de um filme especifico no serviço e envia para a view.
     * @param imdbID - Id do filme
     */
    override fun onLoadMovieDetails(imdbID: String) {
        val webCliente = MovieServiceImpl()
        webCliente.getMovieDetails(imdbID, object : MovieServiceApi.MovieCallback<MovieDetails> {
            override fun onLoaded(result: MovieDetails) {
                view.displayMovieDetails(result)
            }

            override fun onError(errorId: Int) {
                view.displayErrorMessage(errorId)
            }
        })

    }
}