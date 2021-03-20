package com.abrar.kitabisanonton.core.di

import com.abrar.kitabisanonton.presentation.detailmovie.DetailMovieViewModel
import com.abrar.kitabisanonton.presentation.listpopular.PopularMovieViewModel
import com.abrar.kitabisanonton.presentation.nowplaying.NowPlayingViewModel
import com.abrar.kitabisanonton.presentation.toprated.TopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { PopularMovieViewModel(get(), get())}

    viewModel { TopRatedViewModel(get()) }

    viewModel { NowPlayingViewModel(get()) }

    viewModel { DetailMovieViewModel(get(), get()) }

    single { createGetMovieUseCase(get()) }

    single { createMoviesRepository(get()) }
}
