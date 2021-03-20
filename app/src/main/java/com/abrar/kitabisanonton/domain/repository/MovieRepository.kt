package com.abrar.kitabisanonton.domain.repository

import com.abrar.kitabisanonton.domain.model.*

interface MovieRepository {

    suspend fun getPopularMovie(): ResponsePopular

    suspend fun getTopRatedMovie(): ResponseTopRated

    suspend fun getNowPlayingMovie(): ResponseNowPlaying

    suspend fun getDetailMovie(id: Int?): Result

    suspend fun getReviewMovie(id: Int?): ResponseReview
}