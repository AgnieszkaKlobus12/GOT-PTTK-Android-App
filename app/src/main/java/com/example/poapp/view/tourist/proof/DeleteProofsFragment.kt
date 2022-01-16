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

class DeleteProofsFragment : Fragment() {

    private var _binding: FragmentProofListBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: RouteViewModel by activityViewModels()
    private val selectedProofs = mutableListOf<Long>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProofListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.close.text = getString(R.string.delete) //todo kolorek buttona
        binding.close.setOnClickListener {
            if (selectedProofs.isEmpty()) {
                dialogEmptySelection()
            } else {
                dialogConfirmDelete()
            }
        }
        binding.cancelSaveProofs.visibility = View.GONE
        binding.saveProofs.visibility = View.GONE

        binding.proofList.adapter = ProofListAdapter(mViewModel.getRouteProofs(), mViewModel, object : OnProofSelectedListener {
            override fun check(proofId: Long) {
                selectedProofs.add(proofId)
            }

            override fun uncheck(profId: Long) {
                selectedProofs.remove(profId)
            }
        })
    }

    private fun dialogEmptySelection() {
        //todo dialog najpierw wybierz dowód do usunięcia
    }

    private fun dialogConfirmDelete() {
        //todo dialog czy na pewno chcesz usunąć
        //jeśli tak to:
        mViewModel.removeProofs(selectedProofs)
        activity?.supportFragmentManager?.popBackStack("EditProofs", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.nav_host_fragment_activity_save_route,
                EditProofsFragment(mViewModel.route.value!!.id.toLong())
            )
            ?.addToBackStack("EditProofs")
            ?.commit()

        //jeśli nie to:
        return
    }

}