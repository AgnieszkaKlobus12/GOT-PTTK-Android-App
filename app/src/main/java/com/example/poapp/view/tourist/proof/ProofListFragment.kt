package com.example.poapp.view.tourist.proof

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.example.poapp.R
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
            dialogCancel()
        }
        binding.saveProofs.setOnClickListener {
            mViewModel.confirmProofs()
            activity?.supportFragmentManager?.popBackStack("EditProofs", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_save_route,
                    EditProofsFragment(mViewModel.route.value!!.id.toLong())
                )
                ?.addToBackStack("EditProofs")
                ?.commit()
        }
        binding.proofList.adapter = ProofListAdapter(mViewModel.proofsNotConfirmed, mViewModel)

    }

    private fun dialogCancel() {
        //todo dialog czy na pewno anulować
        //jeśli tak:
        mViewModel.deleteUnconfirmedProofs()
        activity?.supportFragmentManager?.popBackStack("EditProofs", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.nav_host_fragment_activity_save_route,
                EditProofsFragment(mViewModel.route.value!!.id.toLong())
            )
            ?.addToBackStack("EditProofs")
            ?.commit()

        //jeśli nie:
        return
    }

}