package com.topanlabs.filmtopan.detail

import com.topanlabs.filmtopan.data.FilmDetailData
import com.topanlabs.filmtopan.data.TvDetailData
import com.topanlabs.filmtopan.di.Koin
import com.topanlabs.filmtopan.utils.Status
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class DetailViewModelTest : KoinTest {
    val detailViewModel by inject<DetailViewModel>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(Koin.appModule)
    }

    @Test
    fun getFilmDetail() {
        val movieId = 567189
        runBlocking {
            val filmDetail = detailViewModel.getFilmDetail(movieId)
            val data = filmDetail.data as FilmDetailData
            assertNotNull(filmDetail)
            assertEquals(filmDetail.status, Status.SUCCESS)
            assertEquals(data.id, movieId)
            System.out.println(data.originalTitle)
        }


    }

    @Test
    fun getTvDetail() {
        val tvId = 85271
        runBlocking {
            val tvDetail = detailViewModel.getTvDetail(tvId)
            val data = tvDetail.data as TvDetailData
            assertNotNull(data)
            assertEquals(tvDetail.status, Status.SUCCESS)
            assertEquals(data.id, tvId)
            System.out.println(data.originalName)
        }
    }

    @Test
    fun getFilmRating() {
        val movieId = 567189
        val expectedRating = "R"
        runBlocking {
            val filmRating = detailViewModel.getFilmRating(movieId)
            assertNotNull(filmRating)
            assertEquals(expectedRating, filmRating)
        }
    }

    @Test
    fun getTvRating() {
        val tvId = 85271
        val expectedRating = "TV-14"
        runBlocking {
            val tvRating = detailViewModel.getTvRating(tvId)
            assertNotNull(tvRating)
            assertEquals(expectedRating, tvRating)
        }
    }
}