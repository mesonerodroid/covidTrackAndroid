package com.smesonero.covidtrack.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.smesonero.covidtrack.domain_model.DataClassCountry
import com.smesonero.covidtrack.R

class CountryListAdapter(private val list: List<DataClassCountry>)
    : RecyclerView.Adapter<CountryViewHolder>() {

    lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        ctx = parent.context
        return CountryViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country: DataClassCountry = list[position]
        holder.bind(country)

        if(holder.adapterPosition%2 ==0){
            holder.itemView.setBackgroundColor(getColor(ctx,R.color.colorLightBlue));
        }
        else{
            holder.itemView.setBackgroundColor(getColor(ctx,R.color.white));
        }
    }

    override fun getItemCount(): Int {

//        Log.e("ADAPTER", "Size: "+list.size)
        return list.size
    }

}