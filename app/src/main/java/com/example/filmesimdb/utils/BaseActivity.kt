package com.example.filmesimdb.utils

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.filmesimdb.R

/**
 * Controles gerais para as Activities do projeto
 */
open class BaseActivity : AppCompatActivity() {
    private lateinit var progressDialog:ProgressDialog

    fun showProgress(show: Boolean) {
        if (show) {
            progressDialog = ProgressDialog.show(this, "", getString(R.string.progress_aguarde))
        }
        else {
            progressDialog.dismiss()
        }
    }

    fun hideKeyboard(view : View){
        val imm  = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
