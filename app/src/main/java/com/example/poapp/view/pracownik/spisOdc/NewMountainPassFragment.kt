package com.example.poapp.view.pracownik.spisOdc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.poapp.R
import com.example.poapp.databinding.FragmentNewMountainPassBinding
import com.example.poapp.viewModel.MountainPassOfficialViewModel

//if id !=0 then edit existing, else add new
class NewMountainPassFragment(private val mountainPassId: Int) : Fragment() {

    private var _binding: FragmentNewMountainPassBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: MountainPassOfficialViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //check if MountainPass exists, save in ViewModel if so
        if (mountainPassId != 0) {
            val mountainPass = mViewModel.getMountainPassOfficial(mountainPassId)[0]
            mViewModel.setMountainPass(mountainPass)
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

        mViewModel.mountainPassOfficial.observe(
            viewLifecycleOwner,
            { mountainPassOfficial ->
                if (mountainPassOfficial.FKpunktPoczatkowy != 0) {
                    binding.newStart.text =
                        mViewModel.getOfficialPoint(mountainPassOfficial.FKpunktPoczatkowy)[0].nazwa
                } else {
                    binding.newStart.text = "-"
                }
                if (mountainPassOfficial.FKpunktKoncowy != 0) {
                    binding.newEnd.text =
                        mViewModel.getOfficialPoint(mountainPassOfficial.FKpunktKoncowy)[0].nazwa
                } else {
                    binding.newEnd.text = "-"
                }
                binding.newPoints.text = mountainPassOfficial.punkty.toString()
                if (mountainPassOfficial.FKpunktPosredni != 0 && mountainPassOfficial.FKpunktPosredni != null) {
                    binding.newThrough.text =
                        mViewModel.getOfficialPoint(mountainPassOfficial.FKpunktPosredni!!)[0].nazwa
                } else {
                    binding.newThrough.text = "-"
                }
                binding.newName.text = mountainPassOfficial.nazwa
                binding.newStatus.text = mountainPassOfficial.status
            })

        binding.editStart.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_mountain_passes_list,
                    NewOfficialPointFragment(
                        mViewModel.mountainPassOfficial.value!!.FKpunktPoczatkowy,
                        0
                    )
                )
                ?.addToBackStack("NewPoint")
                ?.commit()
        }
        binding.editEnd.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_mountain_passes_list,
                    NewOfficialPointFragment(
                        mViewModel.mountainPassOfficial.value!!.FKpunktKoncowy,
                        2
                    )
                )
                ?.addToBackStack("NewPoint")
                ?.commit()
        }
        binding.editPoints.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_mountain_passes_list,
                    EditMountainPassPointsFragment()
                )
                ?.addToBackStack("EditMountainPassPoints")
                ?.commit()
        }
        binding.editThrough.setOnClickListener {
            var id = 0
            if (mViewModel.mountainPassOfficial.value!!.FKpunktPosredni != null) {
                id = mViewModel.mountainPassOfficial.value!!.FKpunktPosredni!!
            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_mountain_passes_list,
                    NewOfficialPointFragment(id, 1)
                )
                ?.addToBackStack("NewPoint")
                ?.commit()
        }
        binding.editName.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_mountain_passes_list,
                    EditMountainPassNameFragment()
                )
                ?.addToBackStack("EditMountainPassName")
                ?.commit()
        }
        binding.editStatus.setOnClickListener {
            //TODO dialog czy na pewno zmienić
        }

        binding.cancelMountainPass.setOnClickListener {
            //TODO dialog czy na pewno anulować
            activity?.supportFragmentManager?.popBackStack()
        }
        binding.saveMountainPass.setOnClickListener {
//            mViewModel.addOdcinekOficjalny(mViewModel.mountainPassOfficial.value)
        }
    }
}