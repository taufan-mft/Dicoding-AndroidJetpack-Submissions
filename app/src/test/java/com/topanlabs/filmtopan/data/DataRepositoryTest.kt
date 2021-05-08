package com.topanlabs.filmtopan.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.topanlabs.filmtopan.db.ArtDao
import com.topanlabs.filmtopan.db.ArtEntity
import com.topanlabs.filmtopan.db.ArtRoomDatabase
import com.topanlabs.filmtopan.network.RetroBuilder
import com.topanlabs.filmtopan.utils.LiveDataTestUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class DataRepositoryTest : KoinTest {
    private lateinit var repository: DataRepository
    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var mockedDao: ArtRoomDatabase

    @Mock
    lateinit var artDao: ArtDao

    var mockedModule = module {
        single { RetroBuilder }
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(mockedModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        `when`(mockedDao.artDao()).thenReturn(artDao)
        repository = DataRepository(get<RetroBuilder>().tmApi, mockedDao.artDao())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testLive() {
        val data: Flow<List<ArtEntity>> = flow {
            val list = listOf(
                ArtEntity(
                    id = 88,
                    title = "raisa",
                    photo = "photo",
                    type = "tv",
                    year = "2020"
                )
            )
            emit(list)
        }
        `when`(artDao.getFavoriteList("tvs")).thenReturn(data)
        val dataRepo: Flow<List<ArtEntity>> = repository.allLikedArts("tvs")
        assertNotNull(dataRepo)
        val dataLive = LiveDataTestUtil.getValue(dataRepo.asLiveData())
        assertEquals(dataLive[0].title, "raisa")
    }


    @Test
    fun getFilms() {
        runBlocking {
            val films = repository.getFilms()
            assertNotNull(films)
            val data = films.results[0].originalTitle
            println(data)
        }
    }

    @Test
    fun getTvs() {
        runBlocking {
            val tvs = repository.getTvs()
            assertNotNull(tvs)
            val data = tvs.results[0].originalName
            println(data)
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