package com.abrar.kitabisanonton.domain.usecase

import com.abrar.kitabisanonton.domain.model.*
import com.abrar.kitabisanonton.domain.repository.MovieRepository
import com.abrar.kitabisanonton.domain.usecase.base.UseCase

class GetMovieUseCase(
    private val movieRepository: MovieRepository
) : UseCase<List<Movie>, Any?>() {

    override suspend fun runPopularMovie(params: Any?): ResponsePopular {
        return movieRepository.getPopularMovie()
    }

    override suspend fun runTopRated(params: Any?): ResponseTopRated {
        return movieRepository.getTopRatedMovie()
    }

    override suspend fun runNowPlaying(params: Any?): ResponseNowPlaying {
        return movieRepository.getNowPlayingMovie()
    }

    override suspend fun runDetailMovie(params: Any?, idMovie: Int?): Result {
        return movieRepository.getDetailMovie(idMovie)
    }

    override suspend fun runReviewMovie(params: Any?): ResponseReview {
        return movieRepository.getReviewMovie(params.toString().toInt())
    }

}