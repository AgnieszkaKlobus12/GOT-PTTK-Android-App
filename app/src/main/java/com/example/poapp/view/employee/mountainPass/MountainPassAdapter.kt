package com.example.poapp.view.employee.mountainPass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.MountainPassOfficial

class MountainPassAdapter(
    private val values: List<MountainPassOfficial>,
    private val onMountainPassClickedListener: OnMountainPassClickedListener
) :
    RecyclerView.Adapter<MountainPassAdapter.MountainPassOfficialItemHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MountainPassOfficialItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_mountain_pass, parent,
            false
        )
        return MountainPassOfficialItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: MountainPassOfficialItemHolder, position: Int) {
        val mountainPass = values[position]
        holder.startPoint.text = mountainPass.FKpunktPoczatkowy.toString() //TODO zamienić na nazwy
        holder.endPoint.text = mountainPass.FKpunktKoncowy.toString()
        holder.passPoints.text = mountainPass.punkty.toString()
        holder.throughPoint.text = mountainPass.FKpunktPosredni.toString()
        holder.name.text = mountainPass.nazwa
        holder.status.text = mountainPass.status
        holder.itemView.setOnClickListener {
            onMountainPassClickedListener.onItemClick(mountainPass)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class MountainPassOfficialItemHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val startPoint: TextView = iv.findViewById(R.id.pass_start)
        val endPoint: TextView = iv.findViewById(R.id.pass_end)
        val passPoints: TextView = iv.findViewById(R.id.pass_points)
        val throughPoint: TextView = iv.findViewById(R.id.pass_through)
        val name: TextView = iv.findViewById(R.id.pass_name)
        val status: TextView = iv.findViewById(R.id.pass_status)
    }

}