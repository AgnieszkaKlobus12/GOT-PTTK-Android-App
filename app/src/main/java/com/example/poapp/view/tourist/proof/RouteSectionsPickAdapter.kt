package com.example.poapp.view.tourist.proof

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.RouteSection
import com.example.poapp.viewModel.RouteViewModel

class RouteSectionsPickAdapter(
    private val values: List<RouteSection>,
    private val mViewModel: RouteViewModel,
    private val onRouteSectionSelectedListener: OnRouteSectionSelectedListener
) :
    RecyclerView.Adapter<RouteSectionsPickAdapter.RouteSectionItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteSectionsPickAdapter.RouteSectionItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_mountain_pass_item, parent, false)
        return RouteSectionItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: RouteSectionsPickAdapter.RouteSectionItemHolder, position: Int) {
        val item = values[position]

        holder.points.visibility = View.GONE
        holder.pointsLabel.visibility = View.GONE

        holder.start.text = mViewModel.getStartPointName(item)
        holder.end.text = mViewModel.getEndPointName(item)
        holder.through.text = mViewModel.getThroughPointName(item)
        holder.name.text = mViewModel.getRouteSectionName(item)
        holder.isPoofLabel.text = "Dowód:"
        if (mViewModel.hasProof(item)) { //todo kolorki
            holder.isProof.text = "TAK"
        } else {
            holder.isProof.text = "NIE"
        }
        holder.itemView.setOnClickListener { //todo kolorki
            if (holder.selected.isChecked) {
                holder.selected.isChecked = false
                holder.itemView.setBackgroundColor(Color.rgb(0, 0, 0))
                onRouteSectionSelectedListener.uncheck(item.id.toLong())
            } else {
                holder.selected.isChecked = true
                holder.itemView.setBackgroundColor(Color.rgb(150, 150, 150))
                onRouteSectionSelectedListener.check(item.id.toLong())
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class RouteSectionItemHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val start: TextView = iv.findViewById(R.id.pass_start)
        val end: TextView = iv.findViewById(R.id.pass_end)
        val points: TextView = iv.findViewById(R.id.pass_points)
        val pointsLabel: TextView = iv.findViewById(R.id.pass_points_label)
        val through: TextView = iv.findViewById(R.id.pass_through)
        val name: TextView = iv.findViewById(R.id.pass_name)
        val isPoofLabel: TextView = iv.findViewById(R.id.pass_extra_label)
        val isProof: TextView = iv.findViewById(R.id.pass_extra)
        val selected: SwitchCompat = iv.findViewById(R.id.pass_selected)
    }

}