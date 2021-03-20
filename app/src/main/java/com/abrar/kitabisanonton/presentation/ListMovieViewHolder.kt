package com.abrar.kitabisanonton.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abrar.kitabisanonton.domain.model.Result
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import java.text.DateFormat

class ListMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(result: Result?, listener: Listener?) {
        with(itemView) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original/" + result?.posterPath)
                .into(image_poster_movie)

            text_overview_title.visibility = View.GONE
            image_icon_fav.visibility = View.GONE

            text_title_movie.text = result?.title
            text_desc_movie.text = result?.overview
            text_release_date.text = DateFormat.getDateInstance(DateFormat.FULL).format(result?.releaseDate).toString()

            itemView.setOnClickListener {
                listener?.onClickMovie(result)
            }
        }
    }

    interface Listener {
        fun onClickMovie(result: Result?)
    }
}