package com.abrar.kitabisanonton.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abrar.kitabisanonton.R
import com.abrar.kitabisanonton.domain.model.Result

class MoviePopularAdapter(
    var listMovie: List<Result>?,
    val listener: ListMovieViewHolder.Listener
) : RecyclerView.Adapter<ListMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMovieViewHolder {

        return ListMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListMovieViewHolder, position: Int) {
        listMovie?.get(position).let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return listMovie?.size!!
    }
}