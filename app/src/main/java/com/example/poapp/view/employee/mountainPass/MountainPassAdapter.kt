package com.example.poapp.view.employee.mountainPass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.MountainPassOfficial
import com.example.poapp.viewModel.MountainPassOfficialViewModel

class MountainPassAdapter(
    private val values: List<MountainPassOfficial>,
    private val mViewModel: MountainPassOfficialViewModel,
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
        holder.startPoint.text =
            mViewModel.getOfficialPoint(mountainPass.FKpunktPoczatkowy)[0].nazwa
        holder.endPoint.text = mViewModel.getOfficialPoint(mountainPass.FKpunktKoncowy)[0].nazwa
        holder.passPoints.text = mountainPass.punkty.toString()
        if (mountainPass.FKpunktPosredni != null && mountainPass.FKpunktPosredni != 0) {
            holder.throughPoint.text =
                mViewModel.getOfficialPoint(mountainPass.FKpunktPosredni!!)[0].nazwa
        } else {
            holder.throughPoint.text = "-"
        }
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