package com.smesonero.cvdtrack.model_network

data class Country(
    val Date : String,
    val Country : String,
    val CountryCode : String,
    val NewConfirmed: String,
    val TotalConfirmed: String,
    val NewDeaths: String,
    val TotalDeaths: String,
    val NewRecovered: String,
    val TotalRecovered: String
)
