package com.topanlabs.filmtopan.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by taufan-mft on 5/8/2021.
 */
@Database(entities = [ArtEntity::class], version = 1, exportSchema = false)
abstract class ArtRoomDatabase : RoomDatabase() {
    abstract fun artDao(): ArtDao

    companion object {
        @Volatile
        private var INSTANCE: ArtRoomDatabase? = null

        fun getDatabase(context: Context): ArtRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArtRoomDatabase::class.java,
                    "art_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}