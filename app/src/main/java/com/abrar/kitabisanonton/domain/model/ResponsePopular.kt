package com.abrar.kitabisanonton.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abrar.kitabisanonton.data.source.local.converter.ResultConverter

@Entity(tableName = "popular_movie_table")
@TypeConverters(ResultConverter::class)
data class ResponsePopular(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val results: List<Result>?
)