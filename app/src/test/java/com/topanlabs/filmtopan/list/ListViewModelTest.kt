package com.topanlabs.filmtopan.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.data.TmHead
import com.topanlabs.filmtopan.data.TmTvHead
import com.topanlabs.filmtopan.di.Koin
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
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListViewModelTest : KoinTest {
    val listViewModel by inject<ListViewModel>()
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

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getFilm() {
        runBlocking {
            listViewModel.getFilm().observeForever(observer)
            val films: TmHead = repoReal.getFilms()
            val resources = Resource.success(data = films)
            assertNotNull(films)
            verify(observer).onChanged(resources)
        }
    }

    @Test
    fun getTv() {
        runBlocking {
            listViewModel.getTv().observeForever(observer2)
            val tvs: TmTvHead = repoReal.getTvs()
            assertNotNull(tvs)
            val resources = Resource.success(data = tvs)

            verify(observer2).onChanged(resources)
        }
    }
}