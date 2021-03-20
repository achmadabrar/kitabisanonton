package com.abrar.kitabisanonton.core.di

import com.abrar.kitabisanonton.BuildConfig
import com.abrar.kitabisanonton.data.repository.MovieRepositoryImp
import com.abrar.kitabisanonton.data.source.remote.ApiService
import com.abrar.kitabisanonton.domain.repository.MovieRepository
import com.abrar.kitabisanonton.domain.usecase.GetMovieUseCase
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.BASE_URL) }

    single { createOkHttpClient() }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }

    single { GsonConverterFactory.create() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createMoviesRepository(apiService: ApiService): MovieRepository {
    return MovieRepositoryImp(apiService)
}

fun createGetMovieUseCase(movieRepository: MovieRepository): GetMovieUseCase {
    return GetMovieUseCase(movieRepository)
}