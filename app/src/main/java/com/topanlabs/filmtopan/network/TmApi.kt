package com.topanlabs.filmtopan.network

import com.topanlabs.filmtopan.data.*
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by taufan-mft on 5/1/2021.
 */
interface TmApi {
    @GET("trending/movie/day?api_key=7f85d423ec1dba1aab33327dfb3fd290")
    suspend fun getMovies(): TmHead

    @GET("trending/tv/day?api_key=7f85d423ec1dba1aab33327dfb3fd290")
    suspend fun getTvs(): TmTvHead

    @GET("https://api.themoviedb.org/3/movie/{movieID}?api_key=7f85d423ec1dba1aab33327dfb3fd290&language=en-US")
    suspend fun getFilmDetail(@Path("movieID") movieID: Int): FilmDetailData

    @GET("https://api.themoviedb.org/3/tv/{tvID}?api_key=7f85d423ec1dba1aab33327dfb3fd290&language=en-US")
    suspend fun getTvDetail(@Path("tvID") tvID: Int): TvDetailData

    @GET("https://api.themoviedb.org/3/tv/{tvID}/content_ratings?api_key=7f85d423ec1dba1aab33327dfb3fd290")
    suspend fun getTvRating(@Path("tvID") tvID: Int): RatingData

    @GET("https://api.themoviedb.org/3/movie/{movieID}/release_dates?api_key=7f85d423ec1dba1aab33327dfb3fd290&language=en-US")
    suspend fun getFilmRating(@Path("movieID") movieID: Int): RatingFilmData


}