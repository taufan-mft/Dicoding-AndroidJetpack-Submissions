package com.topanlabs.filmtopan.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.data.TmHead
import com.topanlabs.filmtopan.data.TmTvHead
import com.topanlabs.filmtopan.utils.EspressoIdlingResource
import com.topanlabs.filmtopan.utils.LiveDataTestUtil
import com.topanlabs.filmtopan.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [29])
class ListViewModelTest : KoinTest {
    val repoReal by inject<DataRepository>()
    val dispatcher = TestCoroutineDispatcher()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<Any>>

    @Mock
    private lateinit var observer2: Observer<Resource<Any>>

    private lateinit var listViewModel: ListViewModel

    @Mock
    private lateinit var espresso: EspressoIdlingResource

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        listViewModel = ListViewModel(repoReal, espresso)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        stopKoin()
    }

    @Test
    fun getFilm() {


        val films: TmHead = runBlocking { repoReal.getFilms() }
        val resources = Resource.success(data = films)
        listViewModel.getFilmku()
        val value = LiveDataTestUtil.getValue(listViewModel.films)
        assertNotNull(value)
        val data = value.data as TmHead
        println(value)
        listViewModel.films.observeForever(observer)
        verify(observer).onChanged(resources)
        assertEquals(data.totalResults, films.totalResults)


    }

    @Test
    fun getTv() {
        val tvs: TmTvHead = runBlocking { repoReal.getTvs() }
        assertNotNull(tvs)

        val resources = Resource.success(data = tvs)
        listViewModel.getTvku()
        val value = LiveDataTestUtil.getValue(listViewModel.tvs)
        assertNotNull(value)
        val data = value.data as TmTvHead
        listViewModel.tvs.observeForever(observer2)
        verify(observer2).onChanged(resources)
        assertEquals(data.totalResults, tvs.totalResults)

    }
}