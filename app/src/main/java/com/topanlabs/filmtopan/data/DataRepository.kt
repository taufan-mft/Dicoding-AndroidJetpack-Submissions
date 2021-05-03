package com.topanlabs.filmtopan.data

import com.topanlabs.filmtopan.network.TmApi

/**
 * Created by taufan-mft on 5/1/2021.
 */
class DataRepository(val tmApi: TmApi) {
    suspend fun getFilms() = tmApi.getMovies()
    suspend fun getTvs() = tmApi.getTvs()
    suspend fun getFilmDetail(movieID: Int) = tmApi.getFilmDetail(movieID)
    suspend fun getTvDetail(tvID: Int) = tmApi.getTvDetail(tvID)
    suspend fun getFilmRating(movieID: Int) = tmApi.getFilmRating(movieID)
    suspend fun getTvRating(tvID: Int) = tmApi.getTvRating(tvID)
}