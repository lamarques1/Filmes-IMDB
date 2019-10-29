package com.example.feeddribbbleposts.movielist.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.feeddribbbleposts.R
import com.example.feeddribbbleposts.moviedetails.MovieDetailsActivity
import com.example.feeddribbbleposts.movielist.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_filme.view.*

class MovieListAdapter (private val context: Context,
                        private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>(){


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
            val detailsIntent = Intent(context, MovieDetailsActivity::class.java)
            detailsIntent.putExtra("imdbID", movies[position].imdbID)
            context.startActivity(detailsIntent)
            Toast.makeText(context, movies[position].title, Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(movie: Movie){
            itemView.visibility = View.GONE

            val title = itemView.txtTitle
            val year = itemView.txtYear
            val poster = itemView.imgPoster
            val posterUri = Uri.parse(movie.poster)
            val colorDrawable = ColorDrawable(0xFFFFFF)

            title.text = movie.title
            year.text = movie.year
            Picasso.get()
                .load(posterUri)
                .fit().centerCrop()
                .placeholder(colorDrawable)
                .into(poster)

            itemView.visibility = View.VISIBLE
        }
    }
}