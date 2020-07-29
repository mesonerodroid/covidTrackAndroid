package com.smesonero.cvdtrack

import android.app.Application
import com.smesonero.cvdtrack.ddbb.CovidDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()  {
//    @Inject
//    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

//    abstract val db : CovidDatabase

    companion object {
        lateinit var dbb : CovidDatabase
    }

    override fun onCreate() {
        super.onCreate()
//        getDb()
    }
//    public fun getDb(): CovidDatabase {
//        dbb = CovidDatabase.getInstance(applicationContext)
//        return dbb
//    }
}