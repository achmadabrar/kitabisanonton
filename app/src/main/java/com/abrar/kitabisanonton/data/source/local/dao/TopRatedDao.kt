package com.abrar.kitabisanonton.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abrar.kitabisanonton.domain.model.ResponseTopRated

@Dao
interface TopRatedDao {
    @Query("SELECT * FROM top_rated_movie_table")
    suspend fun getTopRated(): ResponseTopRated?

    @Query("DELETE FROM top_rated_movie_table WHERE id = :id")
    suspend fun deleteTopRated(id: Int?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRated(topRated: ResponseTopRated)
}