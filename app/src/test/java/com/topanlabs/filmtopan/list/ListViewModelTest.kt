package com.topanlabs.filmtopan.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.data.TmHead
import com.topanlabs.filmtopan.data.TmTvHead
import com.topanlabs.filmtopan.di.Koin
import com.topanlabs.filmtopan.utils.EspressoIdlingResource
import com.topanlabs.filmtopan.utils.Resource
import kotlinx.coroutines.Dispatchers
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
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListViewModelTest : KoinTest {
    val repoReal by inject<DataRepository>()
    val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(Koin.appModule)
    }

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
    }

    @Test
    fun getFilm() {

            listViewModel.getFilm().observeForever(observer)
        val films: TmHead = runBlocking { repoReal.getFilms() }
        val resources = Resource.success(data = films)
            verify(observer).onChanged(resources)


    }

    @Test
    fun getTv() {
            listViewModel.getTv().observeForever(observer2)
        val tvs: TmTvHead = runBlocking { repoReal.getTvs() }
            assertNotNull(tvs)
            val resources = Resource.success(data = tvs)

            verify(observer2).onChanged(resources)

    }
}