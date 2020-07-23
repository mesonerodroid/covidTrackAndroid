package com.smesonero.covidtrack.service

import android.util.Log
import com.smesonero.covidtrack.DataClassCountry
import com.smesonero.covidtrack.DataClassCovid
import com.smesonero.covidtrack.ddbb.CovidDatabase
import com.smesonero.covidtrack.ddbb.dao.CovidDataDao
import com.smesonero.covidtrack.ddbb.entities.CovidDataEntity
import com.smesonero.covidtrack.model_network.CovidInfoData
import java.text.NumberFormat

// ahora no quiero que sea injectable, lo creo en la factory del viewmodel
class CovidRepository(dao: CovidDataDao) {
    // we can use the application context in init or field initialisation

    var client: CovidDtService = createRetrofitWS()

    var daoo = dao

    lateinit var db : CovidDatabase
//    @Inject
//    public fun CovidRepository(covidDataDao: CovidDataDao) {       //parece que se debe llamar igual
//       // db = covidDatabase
//        daoo = covidDataDao
//    }

//    @Inject     //no va a funcionar
//    fun Repository(pokeDao: CovidDataDao) {
//        Log.e("REPOSITORY", "inject del dao: "+pokeDao)
//        daoo = pokeDao
//    }


    //lo suyo seria encadenar esta con el check.
    suspend fun getAllData(): DataClassCovid
//        try{
    {
//        daoo = AppModule.db.covidDataDao()
        lateinit var dataclass:DataClassCovid
        try {
            //Intentar obtener datos más actualizados de servidor
            var respuesta = client.getInfo()
            if (respuesta.isSuccessful) {       //estaría mejor hacer un mapeo, automático.,.,
                var ret: CovidInfoData? = respuesta.body()
                dataclass = mapeoRespuesta(ret)

                //Comprobar si insertar en base de datos, en caso de que sea más reciente que la última almacenada.
                checkUpdateInfo(dataclass)
            }
            else{   //recuperar de bbdd

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
            lateinit var ret :DataClassCovid

            var dataBd = daoo.getAll().get(0)
            var listaDataCountries : List<DataClassCountry> = listOf()

            ret = DataClassCovid(dataBd.date, dataBd.newconfirmed, dataBd.totalconfirmed, dataBd.newdeaths,
                dataBd.totaldeaths, dataBd.newrecovered,dataBd.totalrecovered,listaDataCountries)
            return ret
        }
        else //devolver vacio
        {
            return DataClassCovid("-","","","","","","",listOf())
        }
    }

    private fun mapeoRespuesta(ret: CovidInfoData?): DataClassCovid {

        var listaCountry = ret?.Countries
        var listaDataCountries : List<DataClassCountry> = listOf()

        if (listaCountry != null && listaCountry.size>0) {
            listaCountry.forEach {
                var country :DataClassCountry = DataClassCountry(

                    it?.Country?:"",
                    it?.CountryCode?:"",
                    it?.NewConfirmed?:"",
                    it?.TotalConfirmed?:"",
                    it?.NewDeaths?:"",
                    it?.TotalDeaths?:"",
                    it?.NewRecovered?:"",
                    it?.TotalRecovered?:""
                )
                listaDataCountries.toMutableList().add(country)
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

//        catch (
//        e: Exception ){
//        Log.e("repository", "Error get data" )}

    fun checkUpdateInfo(infoResponse: DataClassCovid) {

        Log.e("repository", "111" )
        var data = infoResponse
        var body = infoResponse
        Log.e("repository", "222" )
        if (daoo.getAll().size==0){
            Log.e("repository", "no hay, insertar" )
            insertData(data)
//            covidInfInsert.Date= body?.Date
        }
        else if (daoo.getAll().size!=0 && daoo.getAll().get(0).date!=null && !data.date.equals(daoo.getAll().get(0).date)){

            Log.e("repository", "ES DISTINTA, borrar y actuali ")
            daoo.deleteAll()
            insertData(data)
        }
        else{
            Log.e("repository", "es la misma" )
        }
        Log.e("repository", "333" )
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
    }
}