package com.abrar.kitabisanonton.presentation.detailmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abrar.kitabisanonton.R
import com.abrar.kitabisanonton.domain.model.ResponseReview

class ReviewAdapter(
    val resultReview: ResponseReview
) : RecyclerView.Adapter<ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_review_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        resultReview.results[position].let {
            holder.bindItem(it)
        }
    }

    override fun getItemCount(): Int {
        return resultReview.results.size
    }
}