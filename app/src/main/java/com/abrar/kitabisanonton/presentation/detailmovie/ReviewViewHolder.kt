package com.abrar.kitabisanonton.presentation.detailmovie

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abrar.kitabisanonton.domain.model.ResultReview
import kotlinx.android.synthetic.main.item_review_user.view.*

class ReviewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindItem(review: ResultReview?) {
        with(itemView) {
            tv_author.text = "username: " +review?.author
            tv_review.text = "review: " +review?.content
            tv_url.text = "url: "+review?.url
        }
    }
}