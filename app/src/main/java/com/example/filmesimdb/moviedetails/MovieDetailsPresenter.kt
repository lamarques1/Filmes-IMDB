package com.example.filmesimdb.moviedetails

import com.example.filmesimdb.moviedetails.model.MovieDetails
import com.example.filmesimdb.moviedetails.model.Season
import com.example.filmesimdb.service.MovieServiceApi
import com.example.filmesimdb.service.MovieServiceImpl


class MovieDetailsPresenter(val view: MovieDetailsContract.View) :
    MovieDetailsContract.Presenter {

    /**
     * Busca os detalhes de um filme especifico no serviço e envia para a view.
     * @param imdbId - Id do filme
     */
    override fun onLoadMovieDetails(imdbId: String) {
        val webCliente = MovieServiceImpl()
        webCliente.getMovieDetails(imdbId, object : MovieServiceApi.MovieDetailsCallback<MovieDetails> {
            override fun onLoaded(result: MovieDetails) {

                if (result.type == "series"){
                    view.showTabs(true)
                    onLoadSeasonInfo(imdbId, "1")
                }else{
                    view.showTabs(false)
                }

                view.displayMovieDetails(result)
            }

            override fun onError(errorId: Int) {
                view.displayErrorMessage(errorId)
            }
        })

    }

    /**
     * Busca as temporadas de uma série especifica no serviço e envia para a view.
     * @param imdbId - Id da serie
     * @param season - Numero da temporada desejada
     */
    override fun onLoadSeasonInfo(imdbID: String, season: String) {
        val webClient = MovieServiceImpl()
        webClient.getSeason(imdbID, season, object : MovieServiceApi.MovieDetailsCallback<Season> {
            override fun onLoaded(result: Season) {
                view.displaySeasonInfo(result)
            }

            override fun onError(errorId: Int) {
                view.displayErrorMessage(errorId)
            }

        })
    }
}