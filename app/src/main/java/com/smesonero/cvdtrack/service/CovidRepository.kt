package com.smesonero.cvdtrack.service

import android.util.Log
import com.smesonero.cvdtrack.domain_model.DataClassCountry
import com.smesonero.cvdtrack.domain_model.DataClassCountryList
import com.smesonero.cvdtrack.domain_model.DataClassCovid
import com.smesonero.cvdtrack.ddbb.CovidDatabase
import com.smesonero.cvdtrack.ddbb.dao.CountryDataDao
import com.smesonero.cvdtrack.ddbb.dao.CovidDataDao
import com.smesonero.cvdtrack.ddbb.db_entities.CountryDbEntity
import com.smesonero.cvdtrack.ddbb.db_entities.CovidDataEntity
import com.smesonero.cvdtrack.model_network.CovidInfoData
import java.text.NumberFormat
import javax.inject.Inject

// antes no quería que fuera injectable, lo creaba en la factory del viewmodel
// ahora sí quiero que sea injectable, ya no uso factory

class CovidRepository @Inject constructor(dao: CovidDataDao, countryDao : CountryDataDao) {


    var client: CovidDtService = createRetrofitWS()
    var daoo = dao
    var countryDao = countryDao
    lateinit var db : CovidDatabase


    //todo faltaría utilizar retrofit Resource
    suspend fun getAllData(): DataClassCovid

    {
        lateinit var dataclass: DataClassCovid
        try {

            var respuesta = client.getInfo()
            if (respuesta.isSuccessful) {
                var ret: CovidInfoData? = respuesta.body()
                dataclass = mapeoRespuesta(ret)

                //Comprobar si insertar en base de datos, en caso de que sea más reciente que la última almacenada.
                checkUpdateInfo(dataclass)
            }
            else{   //recuperar de bbdd
                dataclass = obtainFromDb()
            }
        }catch (e:Exception){
            //Se ha producido un error en la llamada a WS.  Obtener de base de datos.
            dataclass = obtainFromDb()
        }

        return dataclass
    }


    private fun obtainFromDb(): DataClassCovid {

        if (daoo.getAll().size!=0){

            Log.e("repository", "obtencion de db ")
            lateinit var ret : DataClassCovid

            var dataBd = daoo.getAll().get(0)
            var countriesDb = countryDao.getAllCountry()
            var listaDataCountries : MutableList<DataClassCountry> = mutableListOf()

            countriesDb.forEach {

                var country : DataClassCountry =
                    DataClassCountry(
                        "",
                        it.name,
                        "",
                        it.newconfirmed,
                        it.totalconfirmed,
                        it.newdeaths,
                        it.totaldeaths,
                        it.newrecovered,
                        it.totalrecovered
                    )
                listaDataCountries.add(country)
            }

            ret = DataClassCovid(
                dataBd.date, dataBd.newconfirmed, dataBd.totalconfirmed, dataBd.newdeaths,
                dataBd.totaldeaths, dataBd.newrecovered, dataBd.totalrecovered, listaDataCountries
            )
            return ret
        }
        else //devolver vacio
        {
            return DataClassCovid(
                "-",
                "",
                "",
                "",
                "",
                "",
                "",
                listOf()
            )
        }
    }

    private fun mapeoRespuesta(ret: CovidInfoData?): DataClassCovid {

        var listaCountry = ret?.Countries
        var listaDataCountries : MutableList<DataClassCountry> = mutableListOf()

        if (listaCountry != null && listaCountry.size>0) {
            listaCountry.forEach {
                var country : DataClassCountry =
                    DataClassCountry(

                        it.Date,
                        it?.Country ?: "",
                        it?.CountryCode ?: "",
                        it?.NewConfirmed ?: "",
                        it?.TotalConfirmed ?: "",
                        it?.NewDeaths ?: "",
                        it?.TotalDeaths ?: "",
                        it?.NewRecovered ?: "",
                        it?.TotalRecovered ?: ""
                    )
                listaDataCountries.add(country)
            }
        }

        return DataClassCovid(
            ret?.Date ?: "",
            NumberFormat.getInstance().format(ret?.Global?.NewConfirmed ?: 0.0),
            NumberFormat.getInstance().format(ret?.Global?.TotalConfirmed ?: 0.0),
            NumberFormat.getInstance().format(ret?.Global?.NewDeaths ?: 0.0),
            NumberFormat.getInstance().format(ret?.Global?.TotalDeaths ?: 0.0),
            NumberFormat.getInstance().format(ret?.Global?.NewRecovered ?: 0.0),
            NumberFormat.getInstance().format(ret?.Global?.TotalRecovered ?: 0.0),
            listaDataCountries
        )
    }


    fun checkUpdateInfo(infoResponse: DataClassCovid) {

        Log.e("repository", "111" )
        var data = infoResponse
        var body = infoResponse
        Log.e("repository", "222" )
        if (daoo.getAll().size==0){
            Log.e("repository", "no hay, insertar" )
            insertData(data)
        }
        else if (true){

            Log.e("repository", "ES DISTINTA")
            daoo.deleteAll()
            countryDao.deleteAll()
            insertData(data)
        }
        else{
            Log.e("repository", "es la misma" )
        }
    }

    private fun insertData(data: DataClassCovid) {
        var covidInfInsert = CovidDataEntity(
            0, data.date,
            data.newConfirmed,
            data.totalConfirmed,
            data.newDeaths,
            data.totalDeaths,
            data.newRecovered,
            data.totalRecovered
        )
        daoo.insertAll(covidInfInsert)


        var countryInsertList :MutableList<CountryDbEntity>
        countryInsertList = mutableListOf()

        data.countryList!!.forEach {

            var countryInsert = CountryDbEntity(
                0,
                it.country,
                it.newConfirmed,
                it.totalConfirmed,
                it.newDeaths,
                it.totalDeaths,
                it.newRecovered,
                it.totalRecovered
            )
            countryDao.insertCountry(countryInsert)
            countryInsertList.add(countryInsert)
        }
    }


    /*
suspend fun getCountriesData(): List<DataClassCountry>
//        try{
{
//        daoo = AppModule.db.covidDataDao()
    lateinit var dataclassCountryList: DataClassCountryList
    try {
        //Intentar obtener datos más actualizados de servidor
        var respuesta = client.getInfo()
        if (respuesta.isSuccessful) {       //estaría mejor hacer un mapeo, automático.,.,
            var ret: CovidInfoData? = respuesta.body()
            dataclassCountryList = mapeoRespuestaCountry(ret)

            //Comprobar si insertar en base de datos, en caso de que sea más reciente que la última almacenada.
            checkUpdateInfo(dataclassCountryList)
        }
        else{   //recuperar de bbdd

        }
    }catch (e:Exception){

        //Se ha producido un error en la llamada a WS.  Obtener de base de datos.
        dataclass = obtainFromDb()
    }

    return dataclass
}

*/
}