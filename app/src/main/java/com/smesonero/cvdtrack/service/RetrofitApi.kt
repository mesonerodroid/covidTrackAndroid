package com.smesonero.cvdtrack.service

import com.smesonero.cvdtrack.model_network.CovidInfoData
import retrofit2.Response
import retrofit2.http.GET

/**
 * Lego REST API access points
 */
interface CovidService {

    companion object {
        const val ENDPOINT = "https://api.covid19api.com/"
    }

//    @GET("summary/")
//    suspend fun getSets(@Query("page") page: Int? = null,
//                        @Query("page_size") pageSize: Int? = null,
//                        @Query("theme_id") themeId: Int? = null,
//                        @Query("ordering") order: String? = null): Response<ResultsResponse<CovidInfoData>>

    @GET("summary/")
    suspend fun getInfo(): Response<CovidInfoData>

}

/**
 * A {@see RequestInterceptor} that adds an auth token to requests
 */
//class AuthInterceptor(private val accessToken: String) : Interceptor {
//
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request().newBuilder().addHeader(
//            "Authorization", "key " + accessToken).build()
//        return chain.proceed(request)
//    }
//}

/**
 * Abstract Base Data source class with error handling
 */

/**
 *
 * In order to handle errors lets write BaseDataSource class, which takes Retrofit
 * Response object and transform it either to Success or Error. This state will be communicated to UI eventually via Repository -> ViewModel -> Fragment.
 */
/*
*
       Suspending functions are at the center of everything coroutines. A suspending function is simply a function that can be paused
*      and resumed at a later time. They can execute a long running operation and wait for it to complete without blocking.
* */
abstract class BaseDataSource {     //ni puta idea de como funciona esto. se supone que es una corrutina no.

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        return Result.error("Network call has failed for a following reason: " + message)
    }

}

/**
 * A generic class that holds a value with its loading status.
 *
 * Result is usually created by the Repository classes where they return
 * `LiveData<Result<T>>` to pass back the latest data to the UI with its fetch status.
 */

data class Result<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Result<T> {
            return Result(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null)
        }
    }
}

//data class ResultsResponse<T>(
//    @SerializedName("count")
//    val count: Int,
//    @SerializedName("next")
//    val next: String? = null,
//    @SerializedName("previous")
//    val previous: String? = null,
//    @SerializedName("results")
//    val results: List<T>
//)

/**
 * Dependency injection module to provide core data functionality
 */
//object CoreDataModule {
//
//    fun createLegoSetRepository() = LegoSetRepository(createLegoSetRemoteDataSource())
//
//    fun createLegoSetRemoteDataSource() = provideCovidSetRemoteDataSource(provideLegoService(
//        providePrivateOkHttpClient(provideOkHttpClient(provideLoggingInterceptor())),
//        provideGsonConverterFactory(provideGson()))
//    )
//
//    fun provideCovidSetRemoteDataSource(covidService: CovidService)
//            = LegoSetRemoteDataSource(covidService)
//
//    fun provideLegoService(okhttpClient: OkHttpClient,
//                           converterFactory: GsonConverterFactory
//    ) = provideService(okhttpClient, converterFactory, LegoService::class.java)
//
//
//
////    fun providePrivateOkHttpClient(
////        upstreamClient: OkHttpClient
////    ): OkHttpClient {
////        return upstreamClient.newBuilder()
////            .addInterceptor(AuthInterceptor("token")).build()
////    }
//
////    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
////        OkHttpClient.Builder().addInterceptor(interceptor)
////            .build()
//
//    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
//        GsonConverterFactory.create(gson)
//
////    fun provideLoggingInterceptor() =
////        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }
//
//    fun provideGson(): Gson = Gson()
//
//    private fun createRetrofit(
//        okhttpClient: OkHttpClient,
//        converterFactory: GsonConverterFactory
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://api.covid19api.com/")
//            .client(okhttpClient)
//            .addConverterFactory(converterFactory)
//            .build()
//    }
//
//    private fun <T> provideService(okhttpClient: OkHttpClient,
//                                   converterFactory: GsonConverterFactory, clazz: Class<T>): T {
//        return createRetrofit(okhttpClient, converterFactory).create(clazz)
//    }
//}