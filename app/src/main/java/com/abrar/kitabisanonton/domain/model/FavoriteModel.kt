package com.abrar.kitabisanonton.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abrar.kitabisanonton.data.source.local.converter.ResultConverter
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorite_movie_table")
@TypeConverters(ResultConverter::class)
@Parcelize
data class FavoriteModel (
    @PrimaryKey val results: List<Result>
): Parcelable