package com.smesonero.cvdtrack.dinjection

import android.app.Application
import androidx.room.Room
import com.smesonero.cvdtrack.ddbb.CovidDatabase
import com.smesonero.cvdtrack.ddbb.dao.CountryDataDao
import com.smesonero.cvdtrack.ddbb.dao.CovidDataDao
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
    fun provideCovidDb(application: Application?): CovidDatabase {
        return Room.databaseBuilder(application!!, CovidDatabase::class.java, "Database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideCovidDao(covidDatabase: CovidDatabase): CovidDataDao {
        return covidDatabase.covidDataDao()
    }

    @Provides
    @Singleton
    fun provideCountryDao(covidDatabase: CovidDatabase): CountryDataDao {
        return covidDatabase.covidCountryDao()
    }
}