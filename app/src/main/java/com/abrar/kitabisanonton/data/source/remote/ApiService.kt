package com.abrar.kitabisanonton.data.source.remote

import com.abrar.kitabisanonton.domain.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object{
        const val TOP_RATED = "movie/top_rated"
        const val POPULAR = "movie/popular"
        const val NOW_PLAYING = "movie/now_playing"
        const val REVIEW = "movie/{movie_id}/reviews"
        const val DETAILS = "movie/{movie_id}"
    }

    @GET (TOP_RATED)
    suspend fun getTopRated(
        @Query("api_key") api_key: String = "b80bdee7c041098a98259a34ec0c4178"
    ): ResponseTopRated

    @GET(POPULAR)
    suspend fun getPopular(
        @Query("api_key") api_key: String = "b80bdee7c041098a98259a34ec0c4178"
    ): ResponsePopular

    @GET(NOW_PLAYING)
    suspend fun getNowPlaying(
        @Query("api_key") api_key: String = "b80bdee7c041098a98259a34ec0c4178"
    ) : ResponseNowPlaying

    @GET(REVIEW)
    suspend fun getReview(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = "b80bdee7c041098a98259a34ec0c4178"
    ) : ResponseReview

    @GET(DETAILS)
    suspend fun getDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = "b80bdee7c041098a98259a34ec0c4178"
    ) : Result
}