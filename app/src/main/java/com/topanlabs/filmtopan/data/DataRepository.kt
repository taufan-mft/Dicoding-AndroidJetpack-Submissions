package com.topanlabs.filmtopan.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.topanlabs.filmtopan.db.ArtDao
import com.topanlabs.filmtopan.db.ArtEntity
import com.topanlabs.filmtopan.network.TmApi

/**
 * Created by taufan-mft on 5/1/2021.
 */
class DataRepository(private val tmApi: TmApi, private val artDao: ArtDao) {
    suspend fun getFilms() = tmApi.getMovies()
    suspend fun getTvs() = tmApi.getTvs()
    suspend fun getFilmDetail(movieID: Int) = tmApi.getFilmDetail(movieID)
    suspend fun getTvDetail(tvID: Int) = tmApi.getTvDetail(tvID)
    suspend fun getFilmRating(movieID: Int) = tmApi.getFilmRating(movieID)
    suspend fun getTvRating(tvID: Int) = tmApi.getTvRating(tvID)
    fun allLikedArts(type: String): LiveData<PagedList<ArtEntity>> =
        LivePagedListBuilder(artDao.getFavoriteList(type), 20).build()

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

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun searchArt(id: Int): Int {
        return artDao.searchArt(id)
    }
}