package com.smesonero.cvdtrack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smesonero.cvdtrack.domain_model.DataClassCountry
import com.smesonero.cvdtrack.R

class CountryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.countrylistitem, parent, false)) {
    private var countryText: TextView? = null
    private var newCasesText: TextView? = null
    private var totalCasesText: TextView? = null
    private var newDeathsText: TextView? = null
    private var totalDeathsText: TextView? = null


    init {
        countryText = itemView.findViewById(R.id.country_name)
        newCasesText = itemView.findViewById(R.id.country_newcases)
        totalCasesText = itemView.findViewById(R.id.country_totalcases)

        newDeathsText = itemView.findViewById(R.id.country_newdeath)

        totalDeathsText = itemView.findViewById(R.id.country_totaldeath)

    }

    fun bind(country: DataClassCountry) {
        countryText?.text   = country.country.capitalize()
        newCasesText?.text   = country.newConfirmed
        totalCasesText?.text   = country.totalConfirmed
        newDeathsText?.text   = country.newDeaths
        totalDeathsText?.text = country.totalDeaths


    }

}