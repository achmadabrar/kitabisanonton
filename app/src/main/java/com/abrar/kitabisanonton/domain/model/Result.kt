package com.abrar.kitabisanonton.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abrar.kitabisanonton.data.source.local.converter.DateTypeConverter
import com.abrar.kitabisanonton.data.source.local.converter.ResultConverter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "result_table")
@Parcelize
data class Result (
    @SerializedName("poster_path")
    val posterPath: String,
    @PrimaryKey val id: Long,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val title: String?,
    @SerializedName("vote_average")
    val vote: Double,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: Date,
    @SerializedName("original_title")
    val titleDetail: String?
): Parcelable