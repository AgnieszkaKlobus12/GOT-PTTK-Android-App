package com.example.poapp.view.tourist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.MountainPassOfficial
import com.example.poapp.model.entity.MountainPassUser
import com.example.poapp.viewModel.MountainPassListViewModel


class MountainPassPickAdapter<E>(
    private val values: List<E>,
    private val mViewModel: MountainPassListViewModel
) :
    RecyclerView.Adapter<MountainPassPickAdapter<E>.MountainPassItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountainPassItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_mountain_pass_pick, parent,
            false
        )
        return MountainPassItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: MountainPassItemHolder, position: Int) {
        val item = values[position]
        if (item is MountainPassOfficial) {
            holder.start.text = mViewModel.getOfficialPoint(item.FKpunktPoczatkowy).nazwa
            holder.end.text = mViewModel.getOfficialPoint(item.FKpunktKoncowy).nazwa
            holder.points.text = item.punkty.toString()
            holder.through.text =
                item.FKpunktPosredni?.let { mViewModel.getOfficialPoint(it).nazwa }
            holder.name.text = item.nazwa
        } else if (item is MountainPassUser) {
            holder.start.text = item.FKpunktPoczatkowyOficjalny?.let {
                mViewModel.getOfficialPoint(it).nazwa
            }
            holder.start.text = item.FKpunktPoczatkowyWlasny?.let {
                mViewModel.getUserPoint(it).nazwa
            }
            holder.end.text = item.FKpunktKoncowyOficjalny?.let {
                mViewModel.getOfficialPoint(it).nazwa
            }
            holder.end.text = item.FKpunktKoncowyWlasny?.let {
                mViewModel.getUserPoint(it).nazwa
            }
            holder.points.text = item.punkty.toString()
            holder.through.text = item.FKpunktPosredniOficjalny?.let {
                mViewModel.getOfficialPoint(it).nazwa
            }
            holder.through.text = item.FKpunktPosredniWlasny?.let {
                mViewModel.getUserPoint(it).nazwa
            }
            holder.name.text = item.nazwa
        }
    }

    override fun getItemCount(): Int = values.size

    inner class MountainPassItemHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val start: TextView = iv.findViewById(R.id.pass_start)
        val end: TextView = iv.findViewById(R.id.pass_end)
        val points: TextView = iv.findViewById(R.id.pass_points)
        val through: TextView = iv.findViewById(R.id.pass_through)
        val name: TextView = iv.findViewById(R.id.pass_name)
    }
}