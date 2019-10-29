package com.example.feeddribbbleposts.movielist.service

import android.os.AsyncTask
import android.util.Log
import com.example.feeddribbbleposts.movielist.model.MovieList
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL

class MovieListService(private val title: String) : AsyncTask<Void, Void, MovieList>() {

    override fun doInBackground(vararg params: Void?): MovieList? {
        var resposta = ""

        if (!title.isEmpty()) {
            try {
                val url = URL("http://www.omdbapi.com/?s=$title&apikey=e2a2df13")

                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                connection.setRequestProperty("Content-type", "application/json")
                connection.setRequestProperty("Accept", "application/json")
                connection.doOutput = true
                connection.connectTimeout = 5000
                connection.connect()
                resposta = url.readText()

                return Gson().fromJson(resposta, MovieList::class.java)

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return null
    }
}