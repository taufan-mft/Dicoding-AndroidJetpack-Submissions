package com.topanlabs.filmtopan.detail

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun before() {
        detailViewModel = DetailViewModel()
    }

    @Test
    fun getFilm() {
        val film = detailViewModel.getFilm("Creed")
        assertNotNull(film)
        assertEquals("Creed", film.name)
    }

    @Test
    fun getTv() {
        val tv = detailViewModel.getTv("Hanna")
        assertNotNull(tv)
        assertEquals("Hanna", tv.name)
    }
}