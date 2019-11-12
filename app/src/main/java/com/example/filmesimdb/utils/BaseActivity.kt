package com.example.filmesimdb.utils

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmesimdb.R

/**
 * Controles gerais para as Activities do projeto
 */
open class BaseActivity : AppCompatActivity() {
    private lateinit var progressDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showProgress(show: Boolean) {
        if (show) {
            progressDialog = ProgressDialog.show(this, "", getString(R.string.progress_aguarde))
        }
        else {
            progressDialog.dismiss()
        }
    }
}
