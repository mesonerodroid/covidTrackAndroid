package com.smesonero.covidtrack.service

import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class CovidRemoteDataSource @Inject constructor(private val service: CovidService) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getInfo() }

}