package com.abrar.kitabisanonton.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abrar.kitabisanonton.data.source.local.converter.DateTypeConverter
import com.abrar.kitabisanonton.data.source.local.dao.FavoriteMovieDao
import com.abrar.kitabisanonton.data.source.local.dao.NowPlayingDao
import com.abrar.kitabisanonton.data.source.local.dao.PopularDao
import com.abrar.kitabisanonton.data.source.local.dao.TopRatedDao
import com.abrar.kitabisanonton.domain.model.*

@Database(
    entities = [ResponseTopRated::class, ResponsePopular::class, ResponseNowPlaying::class, Result::class],
    version = 5,
    exportSchema = true
)
@TypeConverters(DateTypeConverter::class)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun topRatedDao(): TopRatedDao
    abstract fun popularMovieDao(): PopularDao
    abstract fun nowPlayingMovieDao(): NowPlayingDao
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}