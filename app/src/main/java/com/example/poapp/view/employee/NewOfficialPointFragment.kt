package com.example.poapp.view.employee

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.poapp.R
import com.example.poapp.databinding.FragmentNewOfficialPointBinding
import com.example.poapp.model.entity.OfficialPoint
import com.example.poapp.viewModel.MountainPassOfficialViewModel

//if id != 0, then update existing point, else create new
//changeNr - 0 start, 1 through, 2 end
class NewOfficialPointFragment(private val officialPointID: Int, private val changeNr: Int) : Fragment() {
    private var _binding: FragmentNewOfficialPointBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: MountainPassOfficialViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewOfficialPointBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var officialPoint = OfficialPoint(0, "", 0.0, 0.0, 0)

        //if Id != 0 find in database and show existing data
        if (officialPointID != 0) {
            officialPoint = mViewModel.getOfficialPoint(officialPointID)[0]
            val mountainRange = mViewModel.getMountainRange(officialPoint.FKpasmoGorskie)[0]
            binding.mountainRange.setText(mountainRange.nazwa)
            val mountainGroup = mViewModel.getMountainGroup(mountainRange.FKgrupaGorska.toInt())[0]
            binding.mountainGroup.setText(mountainGroup.nazwa)
        }
        binding.officialPointName.setText(officialPoint.nazwa)
        binding.longitude.setText(officialPoint.dlugoscGeo.toString())
        binding.latitude.setText((officialPoint.szerokoscGeo.toString()))

        //if pointID != 0 edit update in database, else add new
        binding.saveOfficialPoint.setOnClickListener {
            officialPoint.nazwa = binding.officialPointName.text.toString()
            officialPoint.dlugoscGeo = binding.longitude.text.toString().toDouble()
            officialPoint.szerokoscGeo = binding.latitude.text.toString().toDouble()

            //find MountainRange by name
            val mountainRangeList = mViewModel.getMountainRange(binding.mountainRange.text.toString())
            if (mountainRangeList.isNotEmpty()) {
                val mountainRange = mountainRangeList[0]
                officialPoint.FKpasmoGorskie = mountainRange.id
            } else {
                //group and range have to exist

                val alertDialog = requireActivity().let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setNeutralButton(R.string.ok) { dialog, _ ->
                            dialog.dismiss()
                        }
                        setTitle(R.string.alert)
                        setMessage(R.string.mountain_range_error_message)
                    }
                    builder.create()
                }
                alertDialog.show()

                return@setOnClickListener
            }

            //add or update
            //each RouteSection has its own points - implementing handling Points UserCase not required
            if (officialPoint.id == 0)
                officialPoint.id = mViewModel.addOfficialPoint(officialPoint).toInt()
            else
                mViewModel.updateOfficialPoint(officialPoint)

            //change data in ViewModel
            when (changeNr) {
                0 -> mViewModel.mountainPassOfficial.value!!.FKpunktPoczatkowy = officialPoint.id
                1 -> mViewModel.mountainPassOfficial.value!!.FKpunktPosredni = officialPoint.id
                else -> mViewModel.mountainPassOfficial.value!!.FKpunktKoncowy = officialPoint.id
            }
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}