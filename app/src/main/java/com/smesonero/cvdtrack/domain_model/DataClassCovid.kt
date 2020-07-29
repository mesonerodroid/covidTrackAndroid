package com.smesonero.cvdtrack.domain_model


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


