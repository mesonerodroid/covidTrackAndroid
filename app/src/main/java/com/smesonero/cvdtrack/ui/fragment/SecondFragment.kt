package com.smesonero.cvdtrack.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.smesonero.cvdtrack.domain_model.DataClassCountry
import com.smesonero.cvdtrack.domain_model.DataClassCovid
import com.smesonero.cvdtrack.R
import com.smesonero.cvdtrack.ddbb.dao.CovidDataDao
import com.smesonero.cvdtrack.ui.adapter.CountryListAdapter
import com.smesonero.cvdtrack.viewmodel.CovidViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.second_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    val TAG = "SECOND FRAGMENT"
    lateinit var dao : CovidDataDao
    val viewModel: CovidViewModel by viewModels()

    @Inject
    fun covidDao(pokeDao: CovidDataDao) {
        Log.e(TAG, "inject del dao: "+pokeDao)
        dao = pokeDao
    }
    lateinit var dataCountryList: MutableList<DataClassCountry>
//  private lateinit var viewModel: SecondViewModel


    override fun onCreateView(

    inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)

        viewModel.covidLIvedata.observe(viewLifecycleOwner, Observer {

            Log.e(TAG , "LiveData, cambio: " + " "+it)
            actualizarUI(it)
        })
    }

    private fun actualizarUI(data: DataClassCovid) {

        progress_countries.visibility=View.INVISIBLE

        dataCountryList = mutableListOf()
        data.countryList!!.forEach {
            dataCountryList.add(it)
        }
        dataCountryList.sortByDescending { it.totalConfirmed.toInt()}

        countries_recy.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CountryListAdapter(
                dataCountryList
            )
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun createDummy() {

        var countryDummy =
            DataClassCountry(
                "11",
                "África del sur y remotamente",
                "aa",
                "69.000",
                "4.500.000",
                "1.507",
                "635.000",
                "a",
                "a"
            )
        dataCountryList.add(countryDummy)
        var countryDummy2 =
            DataClassCountry(
                "11 ",
                "Polinesia francesa",
                "aa",
                "11.000",
                "559.000",
                "27",
                "1.000",
                "a",
                "a"
            )
        dataCountryList.add(countryDummy2)

        var countryDummy3 =
            DataClassCountry(
                "12",
                "Portugal",
                "aa",
                "1",
                "2",
                "3",
                "4",
                "a",
                "a"
            )
        dataCountryList.add(countryDummy3)
        countries_recy.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CountryListAdapter(
                dataCountryList
            )
        }
    }
}