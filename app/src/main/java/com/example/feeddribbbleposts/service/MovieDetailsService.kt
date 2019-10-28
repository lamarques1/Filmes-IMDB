package com.example.feeddribbbleposts.service

import android.os.AsyncTask
import com.example.feeddribbbleposts.model.MovieDetails
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL

class MovieDetailsService(private val imdbID: String) : AsyncTask<Void, Void, MovieDetails>() {

    override fun doInBackground(vararg params: Void?): MovieDetails {
        var resposta = ""

        try {
            val url = URL("http://www.omdbapi.com/?i=$imdbID&apikey=e2a2df13")

            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            connection.setRequestProperty("Content-type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.doOutput = true
            connection.connectTimeout = 5000
            connection.connect()

            resposta = url.readText()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Gson().fromJson<MovieDetails>(resposta, MovieDetails::class.java)
    }
}