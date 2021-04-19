package com.topanlabs.filmtopan.data

import org.junit.Assert.assertEquals
import org.junit.Test

class FilmFactoryTest {

    @Test
    fun getListData() {
        val data = FilmFactory.listData
        assertEquals("A Star is Born", data[0].name)
    }
}