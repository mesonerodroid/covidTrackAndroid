package com.smesonero.covidtrack

import com.smesonero.covidtrack.model_network.CovidInfoData


data class DataClassCovid(
    var date: String,

    //parte global
    val newConfirmed: String,
    val totalConfirmed: String,
    val newDeaths: String,
    val totalDeaths: String,
    val newRecovered: String,
    val totalRecovered: String,

    //parte countries
    val countryList: List<DataClassCountry>?
/*
    constructor(val data : CovidInfoData) {

    }
*/
)


