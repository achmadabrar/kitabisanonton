package com.abrar.kitabisanonton.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String
)