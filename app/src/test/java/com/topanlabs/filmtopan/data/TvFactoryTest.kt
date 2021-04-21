package com.topanlabs.filmtopan.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TvFactoryTest {

    @Test
    fun getListData() {
        val data = TvFactory.listData
        assertNotNull(data)
        assertEquals("Arrow", data[0].name)
        assertEquals(10, data.count())

    }
}