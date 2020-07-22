package com.smesonero.covidtrack

import java.lang.reflect.Constructor

data class DataClassCountry(
    val country : String,
    val countryCode : String,
    val NewConfirmed: String,
    val TotalConfirmed: String,
    val NewDeaths: String,
    val TotalDeaths: String,
    val NewRecovered: String,
    val TotalRecovered: String

  //  val constructor: Constructor()




)
