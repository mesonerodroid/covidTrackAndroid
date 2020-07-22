package com.smesonero.covidtrack

import android.app.Application
import com.smesonero.covidtrack.ddbb.CovidDatabase

class App : Application()  {
//    @Inject
//    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

//    abstract val db : CovidDatabase

    companion object {
        lateinit var dbb : CovidDatabase
    }

    override fun onCreate() {
        super.onCreate()
        getDb()
    }
    public fun getDb(): CovidDatabase {
        dbb = CovidDatabase.getInstance(applicationContext)
        return dbb
    }
}