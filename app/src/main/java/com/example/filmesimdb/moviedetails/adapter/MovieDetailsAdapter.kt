package com.example.filmesimdb.moviedetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmesimdb.R
import com.example.filmesimdb.moviedetails.model.Ratings
import kotlinx.android.synthetic.main.item_rating.view.*

class MovieDetailsAdapter(private val context: Context,
                          private val ratings: List<Ratings>)
    : RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rating, null)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return ratings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rating = ratings[position]
        holder.bindRatings(rating)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindRatings(ratings: Ratings){
            val source = itemView.txtSource
            val value = itemView.txtValue

            source.text = ratings.source
            value.text = ratings.value
        }
    }
}