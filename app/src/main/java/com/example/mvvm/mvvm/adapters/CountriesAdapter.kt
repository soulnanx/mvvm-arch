package com.example.mvvm.mvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.model.Country

class CountriesAdapter(
    val onClick: (Country) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>(){

    private var countries: MutableList<Country> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    fun addAll(countries: MutableList<Country>){
        this.countries.clear()
        this.countries = countries
        notifyDataSetChanged()
    }

    fun add(country: Country){
        this.countries.add(country)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) :  RecyclerView.ViewHolder(view){

        private val nameTv: TextView = view.findViewById(R.id.nameTv)
        private val rankTv: TextView = view.findViewById(R.id.rankTv)

        fun bind(country: Country){
            nameTv.text = country.name
            rankTv.text = country.rank.toString()
        }

    }

}