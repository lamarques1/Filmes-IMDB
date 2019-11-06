package com.example.filmesimdb.movielist.controller

class EmojiControler {

    /**
     * Caso reconheça algum emoji, retorna o titulo do filme correspondente
     * Senão, retorna o proprio texto.
     */
    fun emojiToText(title: String): String{
        when(title){
            "\uD83E\uDD21" -> return "Joker"
            "\uD83E\uDD21\uD83C\uDF88" -> return "It Chapter Two"
            "\uD83E\uDD87" -> return "Batman"
            "\uD83E\uDD81\uD83D\uDC51" -> return "Lion King"

            else -> return title
        }
    }
}