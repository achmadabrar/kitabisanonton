package com.abrar.kitabisanonton.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abrar.kitabisanonton.domain.model.ResponseNowPlaying
import java.util.*

@Dao
interface NowPlayingDao {
    @Query("SELECT * FROM now_playing_movie_table")
    suspend fun getNowPlaying(): ResponseNowPlaying?

    @Query("DELETE FROM now_playing_movie_table WHERE id = :id")
    suspend fun deleteNowPlaying(id: Int?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlaying(nowPlaying: ResponseNowPlaying)
}