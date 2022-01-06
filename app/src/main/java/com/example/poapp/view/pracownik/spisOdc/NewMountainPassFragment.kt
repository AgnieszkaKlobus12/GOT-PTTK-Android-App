package com.example.poapp.view.pracownik.spisOdc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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
            Observer { mountainPassOfficial ->
                binding.newStart.text = mountainPassOfficial.FKpunktPoczatkowy.toString()
                binding.newEnd.text = mountainPassOfficial.FKpunktKoncowy.toString()
                binding.newPoints.text = mountainPassOfficial.punkty.toString()
                binding.newTrough.text = mountainPassOfficial.FKpunktPosredni.toString()
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
                ?.addToBackStack("NewMountainPass")
                ?.commit()
        }
        //TODO
        binding.editEnd.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_mountain_passes_list,
                    NewOfficialPointFragment(
                        mViewModel.mountainPassOfficial.value!!.FKpunktKoncowy,
                        2
                    )
                )
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.editPoints.setOnClickListener {

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
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.editName.setOnClickListener {

        }
        binding.editStatus.setOnClickListener {

        }

        binding.cancelMountainPass.setOnClickListener {

        }
        binding.saveMountainPass.setOnClickListener {

        }
    }
}