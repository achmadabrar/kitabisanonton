package com.abrar.kitabisanonton.domain.usecase.base

import android.util.Log
import com.abrar.kitabisanonton.data.source.local.dao.FavoriteMovieDao
import com.abrar.kitabisanonton.data.source.local.dao.PopularDao
import com.abrar.kitabisanonton.domain.exception.traceErrorException
import com.abrar.kitabisanonton.domain.model.*
import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

abstract class UseCase<Type, in Params>() where Type : Any {

    abstract suspend fun runPopularMovie(params: Params? = null): ResponsePopular
    abstract suspend fun runTopRated(params: Params? = null): ResponseTopRated
    abstract suspend fun runNowPlaying(params: Params? = null): ResponseNowPlaying
    abstract suspend fun runDetailMovie(params: Params? = null, idMovie: Int? = null): Result
    abstract suspend fun runReviewMovie(params: Params? = null): ResponseReview


    fun invokePopular(popularDao: PopularDao, scope: CoroutineScope, params: Params?, onResult: UseCasePopularMovie<ResponsePopular>) {

        scope.launch {
            val popularMovie = popularDao.getPopularMovie()
            if (popularMovie?.results.isNullOrEmpty()) {
                try {
                    val result = runPopularMovie(params)
                    onResult.onSuccess(result)
                    val popularSave = popularDao.insertPopularMovie(result)
                    Log.d("popularlogsave", "popular disimpan = $popularSave")
                } catch (e: CancellationException) {
                    e.printStackTrace()
                    onResult.onError(traceErrorException(e))
                } catch (e: Exception) {
                    e.printStackTrace()
                    onResult.onError(traceErrorException(e))
                }
            } else {
                onResult.onSuccess(popularMovie!!)
            }
        }
    }

    fun invokeTopRated(scope: CoroutineScope, params: Params?, onResult: UseCaseTopRated<ResponseTopRated>) {

        scope.launch {
            try {
                val result = runTopRated(params)
                onResult.onSuccess(result)
            } catch (e: CancellationException) {
                e.printStackTrace()
                onResult.onError(traceErrorException(e))
            } catch (e: Exception) {
                e.printStackTrace()
                onResult.onError(traceErrorException(e))
            }
        }
    }

    fun invokeNowPlaying(scope: CoroutineScope, params: Params?, onResult: UseCaseNowPlaying<ResponseNowPlaying>) {

        scope.launch {
            try {
                val result = runNowPlaying(params)
                onResult.onSuccess(result)
            } catch (e: CancellationException) {
                e.printStackTrace()
                onResult.onError(traceErrorException(e))
            } catch (e: Exception) {
                e.printStackTrace()
                onResult.onError(traceErrorException(e))
            }
        }
    }

    fun invokeDetailMovie(movieId: Int, scope: CoroutineScope, params: Params?, onResult: UseCaseDetailMovie<Result>) {

        scope.launch {
            try {
                val result = runDetailMovie(params, movieId)
                onResult.onSuccessDetail(result)
            } catch (e: CancellationException) {
                e.printStackTrace()
                onResult.onError(traceErrorException(e))
            } catch (e: Exception) {
                e.printStackTrace()
                onResult.onError(traceErrorException(e))
            }
        }
    }

    fun invokeReviewMovie(scope: CoroutineScope, params: Params?, onResult: UseCaseReviewMovie<ResponseReview>) {

        scope.launch {
            try {
                val result = runReviewMovie(params)
                onResult.onSuccessReview(result)
            } catch (e: CancellationException) {
                e.printStackTrace()
                onResult.onError(traceErrorException(e))
            } catch (e: Exception) {
                e.printStackTrace()
                onResult.onError(traceErrorException(e))
            }
        }
    }

    fun invokeInsertFavoriteMovie(favoriteMovieDao: FavoriteMovieDao, scope: CoroutineScope, selectedMovie:Result?, onResult: UseCaseFavoriteMovie<Result>) {
        val movies = mutableListOf<Result?>()
        movies.add(selectedMovie)
        scope.launch {
            favoriteMovieDao.deleteFavoriteMovie(selectedMovie?.id?.toInt()!!)
            favoriteMovieDao.insertFavoriteMovie(movies)
            val favoriteMovie = favoriteMovieDao.getFavoriteMovie()
            onResult.onSuccess(favoriteMovie!!)
        }

    }

    fun invokeListFavoriteMovie(favoriteMovieDao: FavoriteMovieDao, scope: CoroutineScope, onResult: UseCaseFavoriteMovie<Result>) {
        scope.launch {
            val favoriteMovie = favoriteMovieDao.getFavoriteMovie()
            onResult.onSuccess(favoriteMovie!!)
        }
    }

    fun invokeRemoveFavoriteMovie(favoriteMovieDao: FavoriteMovieDao, scope: CoroutineScope, selectedId:Int?, onResult: UseCaseFavoriteMovie<Result>) {
        scope.launch {
            favoriteMovieDao.deleteFavoriteMovie(selectedId?.toInt()!!)
            val favoriteMovie = favoriteMovieDao.getFavoriteMovie()
            onResult.onSuccess(favoriteMovie!!)
        }
    }

}