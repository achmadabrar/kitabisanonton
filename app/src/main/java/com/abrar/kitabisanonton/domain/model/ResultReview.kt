package com.abrar.kitabisanonton.domain.model

import androidx.room.Entity

@Entity
data class ResultReview (
    val author: String,
    val content: String,
    val id: String,
    val url: String
)