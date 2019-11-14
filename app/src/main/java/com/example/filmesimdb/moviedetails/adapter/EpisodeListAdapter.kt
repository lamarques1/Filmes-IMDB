package com.example.filmesimdb.moviedetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmesimdb.R
import com.example.filmesimdb.moviedetails.model.Episodes
import kotlinx.android.synthetic.main.item_episode.view.*

class EpisodeListAdapter (
    private val context: Context,
    private val episodes: List<Episodes>) : RecyclerView.Adapter<EpisodeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_episode, null)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = episodes[position]
        holder.bindEpisode(episode)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindEpisode(episodes: Episodes){
            val number = itemView.txtNumber
            val title = itemView.txtTitle
            val released = itemView.txtReleased
            val rating = itemView.txtRating

            number.text = episodes.episode.toString()
            title.text = episodes.title
            released.text = episodes.released
            rating.text = episodes.imdbRating.toString()
        }
    }
}