package com.topanlabs.filmtopan.network

import com.topanlabs.filmtopan.data.TmHead
import retrofit2.http.GET

/**
 * Created by taufan-mft on 5/1/2021.
 */
interface TmApi {
    @GET("trending/movie/day?api_key=7f85d423ec1dba1aab33327dfb3fd290")
    suspend fun getMovies(): TmHead

    @GET("trending/tv/day?api_key=7f85d423ec1dba1aab33327dfb3fd290")
    suspend fun getTvs(): TmHead

}