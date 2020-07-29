//package com.smesonero.covidtrack.dinjection
//
//import android.content.Context
//import android.util.Log
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.sqlite.db.SupportSQLiteDatabase
//import com.smesonero.covidtrack.ddbb.CovidDatabase
//import com.smesonero.covidtrack.ddbb.dao.CovidDataDao
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ApplicationComponent
//import dagger.hilt.android.qualifiers.ApplicationContext
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import javax.inject.Singleton
//
//@Module
//@InstallIn(ApplicationComponent::class)
//object AppModule {
//
//    lateinit var db: CovidDatabase
//
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): CovidDatabase {
//        Log.e("APPMODULE", "el provide datadabes")
//
//        // Make sure a read is made before writing so our onCreate callback is executed first
//        db =  Room.databaseBuilder(
//            context,
//            CovidDatabase::class.java, "database.db"
//        )
//            .addCallback(object : RoomDatabase.Callback() {
//                override fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//                    GlobalScope.launch {
//                        CovidDatabase.aaaa(this@AppModule.db, context) // in companion of MyDatabase
//                    }
//
//                }
//            })
//            .build()
//        return db
//    }
//
//    @Provides
//    @Singleton
//    fun provideObjectDao(database: CovidDatabase): CovidDataDao {
//        Log.e("APPMODULE", "el provide object dao")
//        return database.covidDataDao()
//    }
//
//}