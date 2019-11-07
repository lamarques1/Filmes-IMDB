package com.example.filmesimdb.movielist

import com.example.filmesimdb.movielist.controller.EmojiControler
import com.example.filmesimdb.movielist.model.Movie
import com.example.filmesimdb.service.MovieServiceApi
import com.example.filmesimdb.service.MovieServiceImpl

class MovieListPresenter(val view : MovieListContract.View) :
    MovieListContract.Presenter {

    companion object{
        var PAGE = 1
    }

    /**
     * Busca a lista de filmes no serviço e envia para a view
     * @param title - Filtro da busca
     */
    override fun onLoadMovies(title: String) {
        if (title.trim().isNotEmpty()){
            // Verifica se é um emoji
            val _title = EmojiControler().emojiToText(title.trim())

            val webCliente = MovieServiceImpl()
            webCliente.getMovieList(_title, PAGE.toString(), object : MovieServiceApi.MovieCallback<List<Movie>> {
                override fun onLoaded(result: List<Movie>, totalItems: Int) {
                    if (PAGE == 1) {
                        view.displayMovies(result)
                    }
                    else{
                        view.getAdapter().updateItems(result)
                        view.getAdapter().notifyDataSetChanged()
                    }
                    PAGE += 1
                }

                override fun onError(errorId: Int) {
                    if (PAGE == 1){
                        view.displayErrorMessage(errorId)
                    }
                }
                override fun onLoaded(result: List<Movie>) {
                    // Not implemented
                }
            })
        }
    }

    fun resetPage(){
        PAGE = 1
    }
}