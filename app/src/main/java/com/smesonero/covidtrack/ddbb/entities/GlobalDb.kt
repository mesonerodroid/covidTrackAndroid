package com.smesonero.covidtrack.ddbb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity



data class GlobalDb(

//    @ColumnInfo(name = "first_name") val firstName: String?,
//    @ColumnInfo(name = "last_name") val lastName: String?

    val newConfirmed: String,
    val totalConfirmed: String,
    val newDeaths: String,
    val totalDeaths: String,
    val newRecovered: String,
    val totalRecovered: String
)