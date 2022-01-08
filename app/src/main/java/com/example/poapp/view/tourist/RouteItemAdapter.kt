package com.example.poapp.view.tourist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.Route

class RouteItemAdapter(private val values: List<Route>) :
    RecyclerView.Adapter<RouteItemAdapter.TrasaItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrasaItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_item, parent,
            false
        )
        return TrasaItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrasaItemHolder, position: Int) {
        val item = values[position]
        holder.date.text = item.dataPrzejscia
        holder.points.text = item.punkty.toString()
        holder.start.text = "TODOOOO"
        holder.end.text =
            "TODOOOO" //trzeba wziąć się dostać do odcinków trasy przez repositor prawdopodobnie
        holder.status.text = item.status
    }

    override fun getItemCount(): Int = values.size

    inner class TrasaItemHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val date: TextView = iv.findViewById(R.id.route_date_value)
        val points: TextView = iv.findViewById(R.id.route_points_label)
        val start: TextView = iv.findViewById(R.id.route_start_label)
        val end: TextView = iv.findViewById(R.id.route_end_label)
        val status: TextView = iv.findViewById(R.id.route_status_label)
    }

}