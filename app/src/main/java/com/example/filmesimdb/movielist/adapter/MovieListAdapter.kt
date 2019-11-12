package com.example.filmesimdb.movielist.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmesimdb.R
import com.example.filmesimdb.moviedetails.MovieDetailsView
import com.example.filmesimdb.movielist.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_filme.view.*
import kotlinx.android.synthetic.main.item_filme.view.imgPoster
import kotlinx.android.synthetic.main.item_filme.view.txtTitle
import kotlinx.android.synthetic.main.item_filme.view.txtYear

class MovieListAdapter (private val context: Context,
                        private var movies: List<Movie>)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>(){

    private var onBottomReachedListener : OnBottomReachedListener? = null


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

        if (position == movies.size - 1){
            onBottomReachedListener?.onBottomReached(position)
        }

        /**
         * Ao clicar no item, inicia a Activity de detalhes com o seu id
         */
        holder.itemView.setOnClickListener {
            val detailsIntent = Intent(context, MovieDetailsView::class.java)
            detailsIntent.putExtra("imdbID", movies[position].imdbID)
            context.startActivity(detailsIntent)
        }
    }

    fun setOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener
    }

    fun updateItems(newMovies: List<Movie>){
        val finalList = ArrayList<Movie>()
        finalList.addAll(movies)
        finalList.addAll(newMovies)
        movies = finalList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    interface OnBottomReachedListener {

        fun onBottomReached(position: Int)

    }
}