package com.example.feeddribbbleposts.service

import android.os.AsyncTask
import com.example.feeddribbbleposts.model.Movie
import com.google.gson.Gson
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MovieDetailsService(private val title: String) : AsyncTask<Void, Void, Movie>() {

    override fun doInBackground(vararg params: Void?): Movie {
        var resposta = ""

        try {
            val url = URL("http://www.omdbapi.com/?t=joker&apikey=e2a2df13")

            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            connection.setRequestProperty("Content-type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.doOutput = true
            connection.connectTimeout = 5000
            connection.connect()

            resposta = url.readText()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Gson().fromJson<Movie>(resposta, Movie::class.java)
    }
}