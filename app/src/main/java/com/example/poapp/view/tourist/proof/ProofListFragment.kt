package com.example.poapp.view.tourist.proof

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.poapp.databinding.FragmentProofListBinding
import com.example.poapp.viewModel.RouteViewModel


class ProofListFragment : Fragment() {

    private var _binding: FragmentProofListBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: RouteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProofListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.close.visibility = View.GONE
        binding.cancelSaveProofs.setOnClickListener {
            //todo
        }
        binding.saveProofs.setOnClickListener {
            //todo
        }
        binding.proofList.adapter = ProofListAdapter(mViewModel.proofsNotSaved, mViewModel)

    }

}