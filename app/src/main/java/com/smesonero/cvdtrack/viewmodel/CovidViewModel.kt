package com.smesonero.cvdtrack.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.smesonero.cvdtrack.service.CovidRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception



//class CovidViewModel @ViewModelInject constructor(
//    private val repo: CovidRepository,
//    @Assisted private val savedStateHandle: SavedStateHandle
//) : ViewModel() {

//}
//class CovidViewModel(repo: CovidRepository, handle: SavedStateHandle) : ViewModel() {
class CovidViewModel @ViewModelInject constructor(
    private val repository: CovidRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(), LifecycleObserver {

//    val db = App.dbb
//    val dao = db.covidDataDao()
//      var repository: CovidRepository =repo

//    init {
//        Log.e("VIEW MODEL", "init viewmodel ,init repository")
//        initRepository()
//    }

    val covidLIvedata = liveData(Dispatchers.IO) {

        try {
//            initRepository()
            val infoResponse = repository.getAllData()      //pide los datos al repositorio. Cómo los obtenga, es transparente
            //repository.check(infoResponse)      //que esto devuelva los datos en formato bueno final, de la base de datos. no el dao de network.
//            repository.checkupdate(infoResponse.body())
            emit(infoResponse)
//            checkupdate(infoResponse.body())

        }catch (e:Exception){
            Log.e("VIEWMODEL", "exception getAlldata "+e)

//                //no quiero hacerlo aquí, quiero hacerlo en el repository
//            obtainDataFromDDBB()
        }
    }


    private fun obtainDataFromDDBB() {



    }
//    private  fun initRepository(){
//        repository = CovidRepository()
//    }

//    fun setDao(dao: CovidDataDao) {
//
//    }
}