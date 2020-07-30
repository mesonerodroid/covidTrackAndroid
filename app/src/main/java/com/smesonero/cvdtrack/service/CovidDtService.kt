package com.smesonero.cvdtrack.service

import com.smesonero.cvdtrack.model_network.CovidInfoData
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

    data class Resultado<out T>(val status: Status, val data: T?, val message: String?) {

        enum class Status {
            SUCCESS,
            ERROR,
            LOADING
        }

        companion object {
            fun <T> success(data: T): Resultado<T> {
                return Resultado(Status.SUCCESS, data, null)
            }

            fun <T> error(message: String, data: T? = null): Resultado<T> {
                return Resultado(Status.ERROR, data, message)
            }

            fun <T> loading(data: T? = null): Resultado<T> {
                return Resultado(Status.LOADING, data, null)
            }
        }
    }

}