package com.example.feeddribbbleposts.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.feeddribbbleposts.R
import com.example.feeddribbbleposts.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_filme.view.*

class MovieAdapter (private val context: Context,
                    private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_filme, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindView(movie)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, movies[position].title, Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(movie: Movie){
            val title = itemView.txtTitle
            val year = itemView.txtYear
            val poster = itemView.imgPoster
            val posterUri = Uri.parse(movie.poster)

            title.text = movie.title
            year.text = movie.year
            poster.setImageURI(posterUri)
            Picasso.get()
                .load(posterUri)
                .fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(poster)

        }
    }
}
