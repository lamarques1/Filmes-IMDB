package com.example.filmesimdb.movielist

import com.example.filmesimdb.movielist.model.MovieList
import com.example.filmesimdb.service.MovieServiceApi
import com.example.filmesimdb.service.MovieServiceImpl

class MovieListPresenter(val view : MovieListContract.View) :
    MovieListContract.Presenter {

    /**
     * Busca a lista de filmes no serviço e envia para a view
     * @param title - Filtro da busca
     */
    override fun onLoadMovies(title: String) {
        if (title.trim().isNotEmpty()){
            val webCliente = MovieServiceImpl()
            webCliente.getMovieList(title, object : MovieServiceApi.MovieCallback<MovieList> {
                override fun onLoaded(result: MovieList) {
                    view.displayMovies(result.movies)
                }

                override fun onError(errorId: Int) {
                    view.displayErrorMessage(errorId)
                }

            })
        }
    }
}