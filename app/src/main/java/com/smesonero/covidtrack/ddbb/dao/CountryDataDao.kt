package com.smesonero.covidtrack.ddbb.dao



import androidx.room.*
import com.smesonero.covidtrack.ddbb.db_entities.CountryDbEntity

@Dao
interface CountryDataDao {


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    @JvmSuppressWildcards
//    abstract fun insertAllCountry(vararg registros: List<CountryDbEntity>)


    @Insert
    fun insertCountry(vararg registros: CountryDbEntity)

    @Query("SELECT * FROM countrydbentity")
    fun getAllCountry(): List<CountryDbEntity>

    @Query("DELETE FROM countrydbentity")
    fun deleteAll()

}