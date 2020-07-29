package com.smesonero.covidtrack.ddbb

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import com.smesonero.covidtrack.ddbb.dao.CountryDataDao
import com.smesonero.covidtrack.ddbb.dao.CovidDataDao
import com.smesonero.covidtrack.ddbb.db_entities.CountryDbEntity
import com.smesonero.covidtrack.ddbb.db_entities.CovidDataEntity

@Database(entities = arrayOf(CovidDataEntity::class, CountryDbEntity::class), version = 4)
abstract class CovidDatabase : RoomDatabase() {

    abstract fun covidDataDao(): CovidDataDao
    abstract fun covidCountryDao(): CountryDataDao

    companion object {
        private var instance: CovidDatabase? = null
        // For Singleton instantiation
//        @Volatile
//        private var instance: CovidDatabase? = null
//
        fun getInstance(): CovidDatabase? {
            return instance
        }

        fun aaaa(db: CovidDatabase, context: Context) {
            Log.e("COVIDDATABASE ", "AAaaaa") //¿consulta fake aqui¿
            instance = db
        }

//        fun onCreate(db: CovidDatabase, context: Context) {
//
//
//
//        }

//        // Create and pre-populate the database. See this article for more details:
//        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
//        private fun buildDatabase(context: Context): CovidDatabase {
//            return Room.databaseBuilder(
//                context,
//                CovidDatabase::class.java, "database.db"
//            ).build()
//
//        }
    }
}