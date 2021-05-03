package com.topanlabs.filmtopan.data

import com.topanlabs.filmtopan.di.Koin.appModule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.inject
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

class DataRepositoryTest : KoinTest {
    val repository by inject<DataRepository>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Test
    fun getFilms() {
        runBlocking {
            val films = repository.getFilms()
            assertNotNull(films)
            val data = films.results[0].originalTitle
            System.out.println(data)
        }
    }

    @Test
    fun getTvs() {
        runBlocking {
            val tvs = repository.getTvs()
            assertNotNull(tvs)
            val data = tvs.results[0].originalName
            System.out.println(data)
        }
    }

    @Test
    fun getFilmDetail() {
        runBlocking {
            val films = repository.getFilms()
            assertNotNull(films)
            val data = films.results[0]
            val filmId = data.id
            val filmDetail = repository.getFilmDetail(filmId)
            assertEquals(data.originalTitle, filmDetail.originalTitle)
        }
    }

    @Test
    fun getTvDetail() {
        runBlocking {
            val tvs = repository.getTvs()
            assertNotNull(tvs)
            val data = tvs.results[0]
            val tvId = data.id
            val tvDetail = repository.getTvDetail(tvId)
            assertEquals(data.originalName, tvDetail.originalName)
        }
    }

    @Test
    fun getFilmRating() {
        runBlocking {
            val films = repository.getFilms()
            assertNotNull(films)
            val data = films.results[0]
            val filmId = data.id
            val rating = repository.getFilmRating(filmId)
            assertNotNull(rating)
            assertEquals(data.id, rating.id)
        }
    }

    @Test
    fun getTvRating() {
        runBlocking {
            val tvs = repository.getTvs()
            assertNotNull(tvs)
            val data = tvs.results[0]
            val tvId = data.id
            val rating = repository.getTvRating(tvId)
            assertNotNull(rating)
            assertEquals(rating.id, data.id)
        }
    }
}