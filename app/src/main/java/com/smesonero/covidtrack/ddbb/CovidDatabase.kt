package com.smesonero.covidtrack.ddbb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.smesonero.covidtrack.ddbb.dao.CovidDataDao
import com.smesonero.covidtrack.ddbb.entities.CovidDataEntity

@Database(entities = arrayOf(CovidDataEntity::class), version = 3)
abstract class CovidDatabase : RoomDatabase() {

    abstract fun covidDataDao(): CovidDataDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CovidDatabase? = null

        fun getInstance(context: Context): CovidDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): CovidDatabase {
            return Room.databaseBuilder(
                context,
                CovidDatabase::class.java, "database-name"
            ).build()

        }
    }
}