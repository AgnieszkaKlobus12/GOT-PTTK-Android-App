package com.example.poapp.view.turysta.trasy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.Route

class TrasaItemAdapter(private val values: List<Route>) :
    RecyclerView.Adapter<TrasaItemAdapter.TrasaItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrasaItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_item, parent,
            false
        )
        return TrasaItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrasaItemHolder, position: Int) {
        val item = values[position]
        holder.data.text = item.dataPrzejscia
        holder.punkty.text = item.punkty.toString()
        holder.start.text = "TODOOOO"
        holder.end.text =
            "TODOOOO" //trzeba wziąć się dostać do odcinków trasy przez repositor prawdopodobnie
        holder.status.text = item.status
    }

    override fun getItemCount(): Int = values.size

    inner class TrasaItemHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val data: TextView = iv.findViewById(R.id.data)
        val punkty: TextView = iv.findViewById(R.id.punkty)
        val start: TextView = iv.findViewById(R.id.poczatek)
        val end: TextView = iv.findViewById(R.id.koniec)
        val status: TextView = iv.findViewById(R.id.status)
    }

}