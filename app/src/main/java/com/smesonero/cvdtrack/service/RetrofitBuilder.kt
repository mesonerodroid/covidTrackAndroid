package com.smesonero.cvdtrack.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



public fun createRetrofitWS(): CovidDtService {

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.covid19api.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(CovidDtService::class.java)
    }
    return webservice
}


