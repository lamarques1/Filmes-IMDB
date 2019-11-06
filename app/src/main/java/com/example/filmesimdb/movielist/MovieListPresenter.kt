package com.example.filmesimdb.movielist

import com.example.filmesimdb.movielist.controller.EmojiControler
import com.example.filmesimdb.movielist.model.Movie
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
            // Verifica se é um emoji
            val _title = EmojiControler().emojiToText(title)

            val webCliente = MovieServiceImpl()
            webCliente.getMovieList(_title, object : MovieServiceApi.MovieCallback<List<Movie>> {
                override fun onLoaded(result: List<Movie>) {
                    view.displayMovies(result)
                }

                override fun onError(errorId: Int) {
                    view.displayErrorMessage(errorId)
                }

            })
        }
    }
}