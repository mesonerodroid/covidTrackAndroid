package com.smesonero.covidtrack.service

import com.smesonero.covidtrack.model_network.CovidInfoData
import retrofit2.Response
import retrofit2.http.GET

interface CovidDtService {



    companion object {
        const val ENDPOINT = "https://api.covid19api.com/"
    }

//    @GET("summary/")
//    suspend fun getSets(@Query("page") page: Int? = null,
//                        @Query("page_size") pageSize: Int? = null,
//                        @Query("theme_id") themeId: Int? = null,
//                        @Query("ordering") order: String? = null): Response<ResultsResponse<CovidInfoData>>

    @GET("summary")
    suspend fun getInfo(): Response<CovidInfoData>

}