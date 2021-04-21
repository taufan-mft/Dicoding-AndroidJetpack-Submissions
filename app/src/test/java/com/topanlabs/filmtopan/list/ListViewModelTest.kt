package com.topanlabs.filmtopan.list

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ListViewModelTest {
    private lateinit var listViewModel: ListViewModel

    @Before
    fun before() {
        listViewModel = ListViewModel()
    }

    @Test
    fun getFilm() {
        val films = listViewModel.getFilm()
        assertNotNull(films)
        assertEquals(10, films.count())
    }

    @Test
    fun getTv() {
        val tvs = listViewModel.getTv()
        assertNotNull(tvs)
        assertEquals(10, tvs.count())
    }
}