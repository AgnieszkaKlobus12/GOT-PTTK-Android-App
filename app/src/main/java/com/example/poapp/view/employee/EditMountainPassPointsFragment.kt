package com.example.poapp.view.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.poapp.databinding.FragmentEditMountainPassPointsBinding
import com.example.poapp.viewModel.MountainPassOfficialViewModel

class EditMountainPassPointsFragment : Fragment() {
    private var _binding: FragmentEditMountainPassPointsBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: MountainPassOfficialViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEditMountainPassPointsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editMountainPassPoints.setText(mViewModel.mountainPassOfficial.value!!.punkty.toString())
        binding.saveMountainPassPoints.setOnClickListener {
            if (binding.editMountainPassPoints.text.toString().toInt() < 0) {
                //TODO dialog "Liczba punktów nie może być mniejsza od zera
                return@setOnClickListener
            }
            mViewModel.mountainPassOfficial.value!!.punkty = binding.editMountainPassPoints.text.toString().toInt()

            activity?.supportFragmentManager?.popBackStack()
        }
    }

}