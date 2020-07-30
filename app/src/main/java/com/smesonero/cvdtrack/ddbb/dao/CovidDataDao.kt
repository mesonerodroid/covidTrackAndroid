package com.smesonero.cvdtrack.ddbb.dao



import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.smesonero.cvdtrack.ddbb.db_entities.CovidDataEntity

@Dao
interface CovidDataDao {
    @Query("SELECT * FROM coviddataentity")
    fun getAll(): List<CovidDataEntity>

    @Delete
    fun delete(covidDataEntity: CovidDataEntity)

    @Query("DELETE FROM coviddataentity")
     fun deleteAll()

    @Insert
    fun insertAll(vararg registros: CovidDataEntity)

}