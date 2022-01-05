package com.example.poapp.view.pracownik.spisOdc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.OdcinekOficjalny

class OdcinekItemAdapter(private val values: List<OdcinekOficjalny>) :
    RecyclerView.Adapter<OdcinekItemAdapter.OdcinekOficjalnyItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OdcinekOficjalnyItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_odcinek, parent,
            false
        )
        return OdcinekOficjalnyItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: OdcinekOficjalnyItemHolder, position: Int) {
        val item = values[position]
        holder.poczatek.text = item.FKpunktPoczatkowy.toString() //zamienić na nazwy!
        holder.koniec.text = item.FKpunktKoncowy.toString()
        holder.punkty.text = item.punkty.toString()
        holder.przez.text = item.FKpunktPosredni.toString()
        holder.nazwa.text = item.nazwa
        holder.status.text = item.status
    }

    override fun getItemCount(): Int = values.size

    inner class OdcinekOficjalnyItemHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val poczatek: TextView = iv.findViewById(R.id.odc_poczatek)
        val koniec: TextView = iv.findViewById(R.id.odc_koniec)
        val punkty: TextView = iv.findViewById(R.id.odc_punkty)
        val przez: TextView = iv.findViewById(R.id.odc_przez)
        val nazwa: TextView = iv.findViewById(R.id.odc_nazwa)
        val status: TextView = iv.findViewById(R.id.odc_status)
    }

}