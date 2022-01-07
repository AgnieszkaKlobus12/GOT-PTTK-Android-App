package com.example.poapp.view.employee.mountainPass

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
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
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            mViewModel.resetMountainPass()
            activity?.supportFragmentManager?.popBackStack()
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
            val alertDialog = requireActivity().let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton(R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                        if (mViewModel.mountainPassOfficial.value!!.status == getString(R.string.active)) {
                            mViewModel.mountainPassOfficial.value!!.status =
                                getString(R.string.removed)
                        } else {
                            mViewModel.mountainPassOfficial.value!!.status =
                                getString(R.string.active)
                        }
                        binding.newStatus.text = mViewModel.mountainPassOfficial.value!!.status
                    }
                    setNegativeButton(R.string.back) { dialog, _ ->
                        dialog.dismiss()
                    }
                    setTitle(R.string.alert)
                    setMessage(R.string.statusChangeMessage)
                }
                builder.create()
            }
            alertDialog.show()
        }

        binding.cancelMountainPass.setOnClickListener {
            //TODO dialog czy na pewno anulować
            activity?.supportFragmentManager?.popBackStack() //jak tak to to, jak nie to return z listenera
        }
        binding.saveMountainPass.setOnClickListener {
            if (mViewModel.mountainPassOfficial.value!!.FKpunktPoczatkowy == 0 || mViewModel.mountainPassOfficial.value!!.FKpunktKoncowy == 0) {
                //TODO Dialog - "Odcinek musi posiadać punkt początkowy i końcowy
                return@setOnClickListener
            }
            val mountainRangeStart =
                mViewModel.getOfficialPoint(mViewModel.mountainPassOfficial.value!!.FKpunktPoczatkowy)[0].FKpasmoGorskie
            val mountainRangeEnd =
                mViewModel.getOfficialPoint(mViewModel.mountainPassOfficial.value!!.FKpunktKoncowy)[0].FKpasmoGorskie
            var mountainRangeThrough = mountainRangeStart
            if (mViewModel.mountainPassOfficial.value!!.FKpunktPosredni != null && mViewModel.mountainPassOfficial.value!!.FKpunktPosredni != 0) {
                mountainRangeThrough =
                    mViewModel.getOfficialPoint(mViewModel.mountainPassOfficial.value!!.FKpunktPosredni!!)[0].FKpasmoGorskie
            } else {
                mViewModel.mountainPassOfficial.value!!.FKpunktPosredni = null
            }
            if (mountainRangeStart != mountainRangeEnd || mountainRangeStart != mountainRangeThrough || mountainRangeEnd != mountainRangeThrough) {
                //TODO DIALOG "Wszstkie punkty odcinka muszą znajdować się w tym samym paśmie górskim"
                return@setOnClickListener
            }
            mViewModel.mountainPassOfficial.value!!.FKpasmoGorskie = mountainRangeStart
            mViewModel.addOfficialPoint(mViewModel.mountainPassOfficial.value!!)
            mViewModel.resetMountainPass()
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}