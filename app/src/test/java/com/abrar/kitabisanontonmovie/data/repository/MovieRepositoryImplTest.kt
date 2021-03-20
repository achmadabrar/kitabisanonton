package com.abrar.kitabisanontonmovie.data.repository

import com.abrar.kitabisanonton.data.repository.MovieRepositoryImp
import com.abrar.kitabisanonton.domain.model.ResponsePopular
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {

    @MockK
    lateinit var movieRepository: MovieRepositoryImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun getPopularMovie() = runBlocking {
        val popularMovie = mockk<ResponsePopular>()
        every { runBlocking { movieRepository.getPopularMovie() } } returns (popularMovie)

        val result = movieRepository.getPopularMovie()
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$popularMovie] must be matches on each other!",
            result,
            CoreMatchers.`is`(popularMovie)
        )
    }

}