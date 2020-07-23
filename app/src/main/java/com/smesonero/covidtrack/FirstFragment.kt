package com.smesonero.covidtrack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.smesonero.covidtrack.ddbb.dao.CovidDataDao
import com.smesonero.covidtrack.service.CovidRepository
import com.smesonero.covidtrack.viewmodel.CovidViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.first_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : Fragment() {

    val TAG = "FIRST FRAGMENT"
    lateinit var dao :CovidDataDao
    lateinit var repository: CovidRepository
    companion object {
        fun newInstance() = FirstFragment()
    }

    @Inject
    fun covidDao(pokeDao: CovidDataDao) {
        Log.e("first fragment", "inject del dao: "+pokeDao)
        dao = pokeDao
    }

    //injeccion del view, model, a través de su factory, creando a su vez el repository
//    val viewModel: CovidViewModel by viewModels{
//        Log.e(TAG, "byviewmodel, obteniendo")
//        MyViewModelFactory(this, CovidRepository(dao),
//            null)
//    }

    //INJECTION a traves de hilt, sin factory
     val viewModel: CovidViewModel by viewModels()



//    private val exampleViewModel: ExampleViewModel by viewModels()


//ahora no lo inyecto lo creo en la factory de viewmodel
//    @Inject
//    fun Repository(repo: CovidRepository) {
//        Log.e("first fragment", "inject del repo: "+repo)
//        repository = repo
//    }

//    @Inject lateinit var repository: CovidRepository



//    private lateinit var viewModel: CovidViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(CovidViewModel::class.java)


//        viewModel.setDao(dao)
        viewModel.covidLIvedata.observe(viewLifecycleOwner, Observer {

            //Log.e("FIRSTFRAGMENT" , "observer, cambio: "+it.isSuccessful + " "+it)
            Log.e("FIRSTFRAGMENT" , "observer, cambio: " + " "+it)
            drawData(it)
        })
    }

    private fun drawData(data: DataClassCovid) {

        if (data.date.equals("-")){ //no se pudo obtener información de servidor y tampoco de bbdd
            Toast.makeText(context, "ERROR AL OBTENER DATOS, COMPRUEBE CONEXIÓN", Toast.LENGTH_LONG).show()
        }
        Log.e(TAG, "draw, date: "+ data.date)

        world_newconfirmed.text =  data.newConfirmed
        world_totalconfirmed.text = data.totalConfirmed
        world_newdeaths.text =data.newDeaths
        world_totaldeaths.text = data.totalDeaths
        world_newrecovery.text = data.newRecovered
        world_totalrecovery.text = data.totalRecovered
    }

}