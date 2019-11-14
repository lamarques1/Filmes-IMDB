package com.example.filmesimdb.movielist

import com.example.filmesimdb.movielist.controller.EmojiController
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
     * A pagina é incrementada a cada busca
     * @param title - Filtro da busca
     */
    override fun onLoadMovies(title: String, type: String) {
        if (title.trim().isNotEmpty()){
            // Verifica se é um emoji
            val _title = EmojiController().emojiToText(title.trim())

            val webCliente = MovieServiceImpl()
            webCliente.getMovieList(_title, PAGE.toString(), type, object : MovieServiceApi.MovieCallback<List<Movie>> {
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

                // Caso não retorne nenhum filme na primeira pagina, envia uma mensagem de erro para a view
                override fun onError(errorId: Int) {
                    if (PAGE == 1){
                        view.displayErrorMessage(errorId)
                    }
                }
            })
        }
    }

    fun resetPage(){
        PAGE = 1
    }
}