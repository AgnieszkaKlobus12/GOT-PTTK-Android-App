package com.example.poapp.view.tourist.proof

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.poapp.databinding.FragmentAddProofLeaderBinding
import com.example.poapp.viewModel.ProofViewModel


class AddProofLeaderFragment : Fragment() {

    private val _binding: FragmentAddProofLeaderBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: ProofViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentAddProofLeaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.leaderProofSave.setOnClickListener {
            TODO()
        }
        binding.leaderProofCancel.setOnClickListener {
            TODO()
        }

    }

}