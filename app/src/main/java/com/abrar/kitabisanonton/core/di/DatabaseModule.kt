package com.abrar.kitabisanonton.core.di

import android.app.Application
import androidx.room.Room
import com.abrar.kitabisanonton.data.source.local.dao.FavoriteMovieDao
import com.abrar.kitabisanonton.data.source.local.dao.NowPlayingDao
import com.abrar.kitabisanonton.data.source.local.dao.PopularDao
import com.abrar.kitabisanonton.data.source.local.dao.TopRatedDao
import com.abrar.kitabisanonton.data.source.local.database.MovieDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val DatabaseModule = module {
    fun provideDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "movies")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providePopularDao(database: MovieDatabase): PopularDao {
        return  database.popularMovieDao()
    }

    fun provideTopRatedDao(database: MovieDatabase): TopRatedDao {
        return database.topRatedDao()
    }

    fun provideNowPlayingDao(database: MovieDatabase): NowPlayingDao {
        return database.nowPlayingMovieDao()
    }

    fun provideFavoriteMovieDao(database: MovieDatabase): FavoriteMovieDao {
        return database.favoriteMovieDao()
    }

    single { provideDatabase(androidApplication()) }
    single { providePopularDao(get()) }
    single { provideTopRatedDao(get()) }
    single { provideNowPlayingDao(get()) }
    single { provideFavoriteMovieDao(get()) }
}