package com.smesonero.covidtrack.model_network

data class Country(
    val Country : String,
    val CountryCode : String,
    val NewConfirmed: String,
    val TotalConfirmed: String,
    val NewDeaths: String,
    val TotalDeaths: String,
    val NewRecovered: String,
    val TotalRecovered: String
)
