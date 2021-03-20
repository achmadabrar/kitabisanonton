package com.abrar.kitabisanonton.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abrar.kitabisanonton.domain.model.Result

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM result_table")
    suspend fun getFavoriteMovie(): List<Result>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(listFavoriteMovie: List<Result?>)

    @Query("DELETE FROM result_table WHERE id = :id")
    suspend fun deleteFavoriteMovie(id: Int)
}