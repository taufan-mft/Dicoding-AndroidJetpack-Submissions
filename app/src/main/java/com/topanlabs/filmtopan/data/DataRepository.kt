package com.topanlabs.filmtopan.data

import com.topanlabs.filmtopan.network.TmApi

/**
 * Created by taufan-mft on 5/1/2021.
 */
class DataRepository(val tmApi: TmApi) {
    suspend fun getFilms() = tmApi.getMovies()
}