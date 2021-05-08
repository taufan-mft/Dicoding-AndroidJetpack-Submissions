package com.topanlabs.filmtopan.db

import androidx.paging.DataSource
import androidx.room.*

/**
 * Created by taufan-mft on 5/8/2021.
 */
@Dao
interface ArtDao {
    companion object {
        const val TABLE_NAME = "art_table"
    }

    @Query("SELECT * FROM $TABLE_NAME WHERE type =:type")
    fun getFavoriteList(type: String): DataSource.Factory<Int, ArtEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(artEntity: ArtEntity)

    @Delete
    suspend fun delete(artEntity: ArtEntity)

    @Query("SELECT COUNT(id) FROM $TABLE_NAME WHERE id =:id")
    suspend fun searchArt(id: Int): Int
}