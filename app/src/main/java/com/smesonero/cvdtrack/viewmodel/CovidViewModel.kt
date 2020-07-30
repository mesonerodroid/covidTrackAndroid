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

    val covidLIvedata = liveData(Dispatchers.IO) {

        try {
            val infoResponse = repository.getAllData()     //pide los datos al repositorio. Cómo los obtenga, es transparente
            emit(infoResponse)

        }catch (e:Exception){
            Log.e("VIEWMODEL", "exception getAlldata "+e)
        }
    }
}