package com.smesonero.covidtrack.model_network

data class GlobalData(
    val Country : String,
    val NewConfirmed: Double,
    val TotalConfirmed: Double,
    val NewDeaths: Double,
    val TotalDeaths: Double,
    val NewRecovered: Double,
    val TotalRecovered: Double


)
