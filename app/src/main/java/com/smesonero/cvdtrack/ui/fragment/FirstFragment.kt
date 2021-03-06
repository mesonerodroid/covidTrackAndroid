package com.smesonero.cvdtrack.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.smesonero.cvdtrack.R
import com.smesonero.cvdtrack.ddbb.dao.CovidDataDao
import com.smesonero.cvdtrack.domain_model.DataClassCovid
import com.smesonero.cvdtrack.util.getLocalDate
import com.smesonero.cvdtrack.viewmodel.CovidViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.first_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : Fragment() {

    val TAG = "FIRST FRAGMENT"
    lateinit var dao :CovidDataDao
    companion object {
        fun newInstance() = FirstFragment()
    }

    @Inject
    fun covidDao(pokeDao: CovidDataDao) {
        Log.e("first fragment", "inject del dao: "+pokeDao)
        dao = pokeDao
    }

    //INJECTION a traves de hilt, sin factory
    val viewModel: CovidViewModel by viewModels()

    //injection del view, model, a través de su factory, creando a su vez el repository
    /*
        val viewModel: CovidViewModel by viewModels{
            Log.e(TAG, "byviewmodel, obteniendo")
            MyViewModelFactory(this, CovidRepository(dao),
                null)
        }
*/

    //creándolo en la factory de viewmodel
    /*
        @Inject
        fun Repository(repo: CovidRepository) {
            Log.e("first fragment", "inject del repo: "+repo)
            repository = repo
        }

        @Inject lateinit var repository: CovidRepository
        private lateinit var viewModel: CovidViewModel
    */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(CovidViewModel::class.java)

        viewModel.covidLIvedata.observe(viewLifecycleOwner, Observer {

            Log.e("FIRSTFRAGMENT" , "observer, cambio: " + " "+it)
            drawData(it)
        })
    }

    private fun drawData(data: DataClassCovid) {

        if (data.date.equals("-")){ //no se pudo obtener información de servidor y tampoco de bbdd
            Toast.makeText(context, "ERROR AL OBTENER DATOS, COMPRUEBE CONEXIÓN", Toast.LENGTH_LONG).show()
        }
        progress_global.visibility=View.INVISIBLE
        Log.e(TAG, "draw, date: "+ data.date)

        world_newconfirmed.text =  data.newConfirmed
        world_totalconfirmed.text = data.totalConfirmed
        world_newdeaths.text =data.newDeaths
        world_totaldeaths.text = data.totalDeaths
        world_newrecovery.text = data.newRecovered
        world_totalrecovery.text = data.totalRecovered
        date_text.text = getLocalDate(data.date)
        animateText()
    }

    private fun animateText() {
        val duration = 1200L
        world_newconfirmed.animate().alpha(1.0f).setDuration(duration);
        world_totalconfirmed.animate().alpha(1.0f).setDuration(duration);
        world_newdeaths.animate().alpha(1.0f).setDuration(duration);
        world_totaldeaths.animate().alpha(1.0f).setDuration(duration);
        world_newrecovery.animate().alpha(1.0f).setDuration(duration);
        world_totalrecovery.animate().alpha(1.0f).setDuration(duration);
    }
}