package com.topanlabs.filmtopan.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by taufan-mft on 5/8/2021.
 */
@Entity(tableName = ArtDao.TABLE_NAME)
data class ArtEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val photo: String,
    val year: String,
    val type: String
)