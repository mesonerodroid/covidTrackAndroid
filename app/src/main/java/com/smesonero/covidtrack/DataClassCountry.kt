package com.smesonero.covidtrack

import java.lang.reflect.Constructor

data class DataClassCountry(

    val date :String,
    val country : String,
    val countryCode : String,
    val newConfirmed: String,
    val totalConfirmed: String,
    val newDeaths: String,
    val totalDeaths: String,
    val newRecovered: String,
    val totalRecovered: String

  //  val constructor: Constructor()




)
