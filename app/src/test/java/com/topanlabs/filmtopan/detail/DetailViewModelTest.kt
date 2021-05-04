package com.topanlabs.filmtopan.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.data.FilmDetailData
import com.topanlabs.filmtopan.data.TvDetailData
import com.topanlabs.filmtopan.di.Koin
import com.topanlabs.filmtopan.utils.EspressoIdlingResource
import com.topanlabs.filmtopan.utils.LiveDataTestUtil
import com.topanlabs.filmtopan.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class DetailViewModelTest : KoinTest {

    private val dispatcher = TestCoroutineDispatcher()
    private val realRepo by inject<DataRepository>()
    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(Koin.appModule)
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        detailViewModel = DetailViewModel(realRepo, espressoIdlingResource)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<Any>>

    @Mock
    private lateinit var observer2: Observer<Resource<Any>>

    @Mock
    private lateinit var espressoIdlingResource: EspressoIdlingResource

    @Test

    fun getFilmDetail() {
        val movieId = 567189

        val films = runBlocking { realRepo.getFilmDetail(movieId) }
        detailViewModel.setFilm(movieId)
        val filmEntities = LiveDataTestUtil.getValue(detailViewModel.selectedFilm)
        val resources = Resource.success(data = films)
        val data = filmEntities.data as FilmDetailData
        assertNotNull(films)
        assertNotNull(data)
        assertEquals(data.originalTitle, films.originalTitle)
        println(filmEntities)
        detailViewModel.selectedFilm.observeForever(observer)
        verify(observer).onChanged(resources)

    }

    @Test
    fun getTvDetail() {
        val tvId = 85271

        val tvExpected = runBlocking { realRepo.getTvDetail(tvId) }
        detailViewModel.setTv(tvId)
        val tvEntities = LiveDataTestUtil.getValue(detailViewModel.selectedTv)
        val resources = Resource.success(data = tvExpected)
        val data = tvEntities.data as TvDetailData
        assertEquals(data.originalName, tvExpected.originalName)
        assertNotNull(tvEntities)
        println(tvEntities)
        detailViewModel.selectedTv.observeForever(observer2)
        verify(observer2).onChanged(resources)

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