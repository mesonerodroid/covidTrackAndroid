package com.smesonero.covidtrack.dinjection

import android.app.Application
import androidx.room.Room
import com.smesonero.covidtrack.ddbb.CovidDatabase
import com.smesonero.covidtrack.ddbb.dao.CovidDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
public class DataBaseModule {

    @Provides
    @Singleton
    fun providePokemonDB(application: Application?): CovidDatabase {
        return Room.databaseBuilder(application!!, CovidDatabase::class.java, "Database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePokeDao(covidDatabase: CovidDatabase): CovidDataDao {
        return covidDatabase.covidDataDao()
    }
}