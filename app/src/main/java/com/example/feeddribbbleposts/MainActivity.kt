package com.example.feeddribbbleposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.feeddribbbleposts.service.MovieDetailsService
import com.example.feeddribbbleposts.service.MovieListService

class MainActivity : AppCompatActivity() {

    private lateinit var etTitulo : EditText
    private lateinit var tvResult : TextView
    private lateinit var btnBusca : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initListeners()
    }

    private fun initViews() {
        etTitulo = findViewById(R.id.etTitulo)
        tvResult = findViewById(R.id.tvResult)
        btnBusca = findViewById(R.id.btnBusca)
    }

    private fun initListeners() {
        btnBusca.setOnClickListener {
            val retorno = MovieListService(etTitulo.text.toString()).execute().get()

            tvResult.visibility = View.VISIBLE
            tvResult.text = retorno.totalResults.toString()

        }
    }

}

