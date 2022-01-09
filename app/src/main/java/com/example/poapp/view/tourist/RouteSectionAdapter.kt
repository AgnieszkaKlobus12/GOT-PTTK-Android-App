package com.example.poapp.view.tourist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.RouteSection
import com.example.poapp.viewModel.NewRouteViewModel

class RouteSectionAdapter(
    private val values: List<RouteSection>,
    private val mViewModel: NewRouteViewModel
) :
    RecyclerView.Adapter<RouteSectionAdapter.RouteSectionItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteSectionItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_route_section_item, parent,
            false
        )
        return RouteSectionItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: RouteSectionItemHolder, position: Int) {
        val item = values[position]
        holder.start.text = mViewModel.getStartPointName(item)
        holder.end.text = mViewModel.getEndPointName(item)
        holder.points.text = mViewModel.getRouteSectionPoints(item).toString()
        holder.through.text = mViewModel.getThroughPointName(item)
        holder.name.text = mViewModel.getRouteSectionName(item)
        holder.proof.text = TODO()
    }

    override fun getItemCount(): Int = values.size

    inner class RouteSectionItemHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val start: TextView = iv.findViewById(R.id.start_text)
        val end: TextView = iv.findViewById(R.id.end_text)
        val points: TextView = iv.findViewById(R.id.route_section_points_value)
        val through: TextView = iv.findViewById(R.id.route_section_through_value)
        val name: TextView = iv.findViewById(R.id.route_section_name_value)
        val proof: TextView = iv.findViewById(R.id.route_section_proof_value)
    }

}