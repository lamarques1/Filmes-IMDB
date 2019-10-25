package com.example.feeddribbbleposts.service

import android.os.AsyncTask
import android.util.Log
import com.example.feeddribbbleposts.model.Movie
import java.lang.StringBuilder
import com.google.gson.Gson
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class HttpService(private val titulo: String) : AsyncTask<Void, Void, Movie>() {

    override fun doInBackground(vararg params: Void?): Movie {
        val resposta = StringBuilder()

        if (!titulo.isEmpty()){
            try {
                val url = URL("http://www.omdbapi.com/?&apikey=e2a2df13&s=" + titulo)

                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.setRequestProperty("Content-type", "application/json")
                connection.setRequestProperty("Accept", "application/json")
                connection.doOutput = true
                connection.connectTimeout = 5000
                connection.connect()
                val scanner = Scanner(url.openStream())
                while (scanner.hasNext()) {
                    resposta.append(scanner.next())
                }
                Log.d("Teste ", resposta.toString())
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return Gson().fromJson<Movie>(resposta.toString(), Movie::class.java)
    }
}
