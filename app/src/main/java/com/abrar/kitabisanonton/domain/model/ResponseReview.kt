package com.abrar.kitabisanonton.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abrar.kitabisanonton.data.source.local.converter.ResultReviewConverter
import java.util.*

@Entity(tableName = "review_user_table")
@TypeConverters(ResultReviewConverter::class)
data class ResponseReview (
    val id: Long,
    val page: Int,
    @PrimaryKey val results: List<ResultReview>
)