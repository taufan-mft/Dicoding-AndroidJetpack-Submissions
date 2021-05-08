package com.topanlabs.filmtopan.data

import androidx.annotation.WorkerThread
import com.topanlabs.filmtopan.db.ArtDao
import com.topanlabs.filmtopan.db.ArtEntity
import com.topanlabs.filmtopan.network.TmApi
import kotlinx.coroutines.flow.Flow

/**
 * Created by taufan-mft on 5/1/2021.
 */
class DataRepository(val tmApi: TmApi, val artDao: ArtDao) {
    suspend fun getFilms() = tmApi.getMovies()
    suspend fun getTvs() = tmApi.getTvs()
    suspend fun getFilmDetail(movieID: Int) = tmApi.getFilmDetail(movieID)
    suspend fun getTvDetail(tvID: Int) = tmApi.getTvDetail(tvID)
    suspend fun getFilmRating(movieID: Int) = tmApi.getFilmRating(movieID)
    suspend fun getTvRating(tvID: Int) = tmApi.getTvRating(tvID)
    fun allLikedArts(type: String): Flow<List<ArtEntity>> = artDao.getFavoriteList(type)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(artEntity: ArtEntity) {
        artDao.insert(artEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(artEntity: ArtEntity) {
        artDao.delete(artEntity)
    }
}