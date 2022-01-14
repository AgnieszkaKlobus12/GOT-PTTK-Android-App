package com.example.poapp.view.tourist.proof

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.poapp.R
import com.example.poapp.databinding.FragmentEditProofsBinding
import com.example.poapp.viewModel.ProofViewModel


class EditProofsFragment(private val routeId: Long) : Fragment() {

    private var _binding: FragmentEditProofsBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: ProofViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentEditProofsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.setRoute(routeId)
        binding.addProof.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_save_route,
                    AddProofFragment()
                )
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.deleteProof.setOnClickListener {
            TODO()
        }
        binding.seeProofs.setOnClickListener {
            TODO()
        }
    }

}