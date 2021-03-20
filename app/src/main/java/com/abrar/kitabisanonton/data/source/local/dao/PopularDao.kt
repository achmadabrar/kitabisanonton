package com.abrar.kitabisanonton.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abrar.kitabisanonton.domain.model.ResponsePopular
import java.util.*

@Dao
interface PopularDao {
    @Query("SELECT * FROM popular_movie_table")
    suspend fun getPopularMovie(): ResponsePopular?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovie(listPopularMovie: ResponsePopular)

    @Query("DELETE FROM popular_movie_table WHERE id = :id")
    suspend fun deletePopularMovie(id: Int)
}