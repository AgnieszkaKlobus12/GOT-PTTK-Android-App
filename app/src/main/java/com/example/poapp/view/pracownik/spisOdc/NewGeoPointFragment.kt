package com.example.poapp.view.pracownik.spisOdc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.poapp.R
import com.example.poapp.databinding.FragmentNewGeoPointBinding
import com.example.poapp.model.entity.MountainGroup
import com.example.poapp.model.entity.MountainRange
import com.example.poapp.model.entity.GeoPoint
import com.example.poapp.viewModel.MountainPassOfficialViewModel

//if id != 0, then update existing point, else create new
//changeNr - 0 start, 1 through, 2 end
class NewGeoPointFragment(private val geoPointID: Int, private val changeNr: Int) : Fragment() {

    private var _binding: FragmentNewGeoPointBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: MountainPassOfficialViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentNewGeoPointBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[MountainPassOfficialViewModel::class.java]

        //default values to show if adding new point
        var geoPoint = GeoPoint(0, "", 0.0, 0.0, 0)

        //if Id != 0 find in database and show existing data
        if (geoPointID != 0) {
            var geoPointList = emptyList<GeoPoint>()
            mViewModel.getOfficialPoint(id).observe(viewLifecycleOwner, { points ->
                points?.let { geoPointList = it }
            })
            geoPoint = geoPointList[0]
            var mountainRangeList = emptyList<MountainRange>()
            mViewModel.getMountainRange(geoPoint.FKpasmoGorskie)
                .observe(viewLifecycleOwner, { mountainRanges ->
                    mountainRanges?.let { mountainRangeList = it }
                })
            binding.mountainRange.setText(mountainRangeList[0].nazwa)
            var mountainGroupList = emptyList<MountainGroup>()
            mViewModel.getMountainGroup(mountainRangeList[0].FKgrupaGorska.toInt())
                .observe(viewLifecycleOwner, { groups ->
                    groups?.let { mountainGroupList = it }
                })
            binding.mountainGroup.setText(mountainGroupList[0].nazwa)
        }
        binding.geoPointName.setText(geoPoint.nazwa)
        binding.longitude.setText(geoPoint.dlugoscGeo.toString())
        binding.latitude.setText((geoPoint.szerokoscGeo.toString()))

        //if pointID != 0 edit update in database, else add new
        binding.saveGeoPoint.setOnClickListener {
            //collect new data
            geoPoint.nazwa = binding.geoPointName.toString()
            geoPoint.dlugoscGeo = binding.longitude.toString().toDouble()
            geoPoint.szerokoscGeo = binding.latitude.toString().toDouble()

            //find MountainRange by name
            var mountainRangeList = emptyList<MountainRange>()
            mViewModel.getMountainRange(binding.mountainRange.toString())
                .observe(viewLifecycleOwner, { range ->
                    range?.let { mountainRangeList = it }
                })
            if (mountainRangeList.isNotEmpty()) {
                val mountainRange = mountainRangeList[0]
                geoPoint.FKpasmoGorskie = mountainRange.id
            } else {
                //group and range have to exist
                //TODO show dialog - "Pasmo Górskie nie występuje w bazie"
            }

            //add or update
            if (geoPoint.id == 0) {
                mViewModel.addGeoPoint(geoPoint)
            } else {
                mViewModel.updateGeoPoint(geoPoint)
            }

            //change data in ViewModel
            when (changeNr) {
                0 -> {
                    mViewModel.mountainPassOfficial.FKpunktPoczatkowy = geoPoint.id
                }
                1 -> {
                    mViewModel.mountainPassOfficial.FKpunktPosredni = geoPoint.id
                }
                else -> {
                    mViewModel.mountainPassOfficial.FKpunktKoncowy = geoPoint.id
                }
            }

            //return
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}