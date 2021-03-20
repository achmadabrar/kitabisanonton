package com.abrar.kitabisanonton.data.repository

import com.abrar.kitabisanonton.data.source.local.dao.NowPlayingDao
import com.abrar.kitabisanonton.data.source.local.dao.PopularDao
import com.abrar.kitabisanonton.data.source.local.dao.TopRatedDao
import com.abrar.kitabisanonton.data.source.remote.ApiService
import com.abrar.kitabisanonton.domain.model.*
import com.abrar.kitabisanonton.domain.repository.MovieRepository

class MovieRepositoryImp(private val apiService: ApiService) : MovieRepository {

    override suspend fun getPopularMovie(): ResponsePopular {
        return apiService.getPopular()
    }

    override suspend fun getTopRatedMovie(): ResponseTopRated {
        return apiService.getTopRated()
    }

    override suspend fun getNowPlayingMovie(): ResponseNowPlaying {
        return apiService.getNowPlaying()
    }

    override suspend fun getDetailMovie(id: Int?): Result {
        return apiService.getDetails(id!!)
    }

    override suspend fun getReviewMovie(id: Int?): ResponseReview {
        return apiService.getReview(id!!)
    }
}