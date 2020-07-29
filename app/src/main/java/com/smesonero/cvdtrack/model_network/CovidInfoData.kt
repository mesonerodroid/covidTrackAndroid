package com.smesonero.cvdtrack.model_network

data class CovidInfoData(
    var Date: String?,
    val Global: GlobalData?,
    val Countries: List<Country>?

)
