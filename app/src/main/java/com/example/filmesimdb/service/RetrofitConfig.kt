package com.example.filmesimdb.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    val BASE_URL = "https://www.omdbapi.com/"
    private var retrofit: Retrofit? = null

    private val gson = GsonBuilder()
        .create()


    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return this.retrofit!!
    }

}