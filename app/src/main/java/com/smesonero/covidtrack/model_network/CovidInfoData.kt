package com.smesonero.covidtrack.model_network

data class CovidInfoData(
    var Date: String?,
    val Global: GlobalData?,
    val Countries: List<Country>?

)
