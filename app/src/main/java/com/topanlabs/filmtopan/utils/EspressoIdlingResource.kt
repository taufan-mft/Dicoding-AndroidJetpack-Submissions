package com.topanlabs.filmtopan.utils

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Created by taufan-mft on 5/3/2021.
 */
class EspressoIdlingResource {
    private val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}