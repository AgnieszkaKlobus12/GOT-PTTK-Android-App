package com.example.poapp.view.pracownik.spisOdc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.poapp.R
import com.example.poapp.databinding.FragmentNewMountainPassBinding
import com.example.poapp.model.entity.MountainGroup
import com.example.poapp.model.entity.MountainPassOfficial
import com.example.poapp.model.entity.MountainRange
import com.example.poapp.viewModel.MountainPassOfficialViewModel

//if id !=0 then edit existing, else add new
class NewMountainPassFragment(private val mountainPassId: Int) : Fragment() {

    private var _binding: FragmentNewMountainPassBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: MountainPassOfficialViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProviders.of(this)[MountainPassOfficialViewModel::class.java]

        primitiveDatabaseStart()
        //check if MountainPass exists, save in ViewModel if so
        if (mountainPassId != 0) {
            var mountainPassList = emptyList<MountainPassOfficial>()
            mViewModel.getMountainPassOfficial(mountainPassId)
                .observe(viewLifecycleOwner, Observer { passes ->
                    passes?.let { mountainPassList = it }
                })
            mViewModel.setMountainPass(mountainPassList[0])
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewMountainPassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editStart.text = mViewModel.mountainPassOfficial.FKpunktPoczatkowy.toString()
        binding.editEnd.text = mViewModel.mountainPassOfficial.FKpunktKoncowy.toString()
        binding.editPoints.text = mViewModel.mountainPassOfficial.punkty.toString()
        binding.editThrough.text = mViewModel.mountainPassOfficial.FKpunktPosredni.toString()
        binding.editName.text = mViewModel.mountainPassOfficial.nazwa
        binding.editStatus.text = mViewModel.mountainPassOfficial.status

        binding.editStart.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_mountain_passes_list,
                    NewGeoPointFragment(mViewModel.mountainPassOfficial.FKpunktPoczatkowy, 0)
                )
                ?.addToBackStack("NewMountainPass")
                ?.commit()
        }
        //TODO
        binding.editEnd.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_mountain_passes_list,
                    NewGeoPointFragment(mViewModel.mountainPassOfficial.FKpunktKoncowy, 2)
                )
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.editPoints.setOnClickListener {

        }
        binding.editThrough.setOnClickListener {
            var id = 0
            if (mViewModel.mountainPassOfficial.FKpunktPosredni != null) {
                id = mViewModel.mountainPassOfficial.FKpunktPosredni!!
            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_mountain_passes_list, NewGeoPointFragment(id, 1))
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.editName.setOnClickListener {

        }
        binding.editStatus.setOnClickListener {

        }

        view.findViewById<Button>(R.id.anuluj_nOd).setOnClickListener {

        }
        binding.saveMountainPass.setOnClickListener {

        }
    }

    //temporary database fill on start - testing data
    private fun primitiveDatabaseStart(){
        var mountainRangeList = emptyList<MountainRange>()
        mViewModel.getAllMountainRanges()
            .observe(this, { mountainRanges ->
                mountainRanges?.let { mountainRangeList = it }
            })
        if(mountainRangeList.isEmpty()){
            mViewModel.addMountainGroup(MountainGroup(0, "Tatry", "Polska", byteArrayOf()))
            var mountainGroup = emptyList<MountainGroup>()
            mViewModel.getMountainGroup("Tatry")
                .observe(this, { mountainRanges ->
                    mountainRanges?.let { mountainGroup = it }
                })
            Toast.makeText(context, mountainGroup.size.toString(), Toast.LENGTH_SHORT).show()
            //mViewModel.addMountainRange(MountainRange(0, "Tatry", byteArrayOf(), mountainGroup[0].id))
        }
    }
}