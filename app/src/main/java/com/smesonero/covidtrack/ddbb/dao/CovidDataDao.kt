package com.smesonero.covidtrack.ddbb.dao



import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.smesonero.covidtrack.ddbb.entities.CovidDataEntity

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
    //    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

//    @Insert
//    fun insertAll(vararg users: User)
}