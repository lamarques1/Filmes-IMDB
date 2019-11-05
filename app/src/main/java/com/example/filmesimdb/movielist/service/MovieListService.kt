package com.example.filmesimdb.movielist.service

import android.os.AsyncTask
import com.example.filmesimdb.movielist.model.MovieList
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL

class MovieListService(private val title: String) : AsyncTask<Void, Void, MovieList>() {

    override fun doInBackground(vararg params: Void?): MovieList? {
        var resposta = ""

        if (!title.isEmpty()) {
            val url = URL("http://www.omdbapi.com/?s=$title&apikey=e2a2df13")
            val connection = url.openConnection() as HttpURLConnection

            try {
                connection.requestMethod = "GET"
                connection.setRequestProperty("Content-type", "application/json")
                connection.setRequestProperty("Accept", "application/json")
                connection.doOutput = true
                connection.connectTimeout = 5000
                connection.connect()

                resposta = url.readText()

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection.disconnect()
            }
        }

        return Gson().fromJson(resposta, MovieList::class.java)
    }
}