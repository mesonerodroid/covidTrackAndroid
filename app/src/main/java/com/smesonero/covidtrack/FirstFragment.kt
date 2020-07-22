package com.smesonero.covidtrack

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.smesonero.covidtrack.viewmodel.CovidViewModel
import kotlinx.android.synthetic.main.first_fragment.*
import java.text.NumberFormat

class FirstFragment : Fragment() {

    val TAG = "FIRST FRAGMENT"
    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: CovidViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CovidViewModel::class.java)

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