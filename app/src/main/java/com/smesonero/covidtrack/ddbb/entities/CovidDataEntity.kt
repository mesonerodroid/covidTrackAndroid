package com.smesonero.covidtrack.ddbb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CovidDataEntity(
    //cada entity es una base de datos.
//    @PrimaryKey val date: String,
//    @ColumnInfo(name = "first_name") val firstName: String?,
//    @ColumnInfo(name = "last_name") val lastName: String?
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "date") val date: String,

    @ColumnInfo(name = "new_confirmed") val newconfirmed: String,
    @ColumnInfo(name = "total_confirmed") val totalconfirmed: String,
    @ColumnInfo(name = "new_deaths") val newdeaths: String,
    @ColumnInfo(name = "total_deaths") val totaldeaths: String,
    @ColumnInfo(name = "new_recovered") val newrecovered: String,
    @ColumnInfo(name = "total_recovered") val totalrecovered: String

//    val global : List<GlobalDb>,
//    val countrises : List<CountryDb>
)