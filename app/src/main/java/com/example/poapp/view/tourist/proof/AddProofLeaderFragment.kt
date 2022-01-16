package com.example.poapp.view.tourist.proof

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.example.poapp.R
import com.example.poapp.databinding.FragmentAddProofLeaderBinding
import com.example.poapp.view.tourist.route.NewRouteFragment
import com.example.poapp.viewModel.RouteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AddProofLeaderFragment(private val new: Boolean = false) : Fragment() {

    private var _binding: FragmentAddProofLeaderBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: RouteViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProofLeaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkLeader()
        binding.leaderProofSave.setOnClickListener {
            when (mViewModel.saveLeaderProof(binding.leaderId.text.toString().toLong())) {
                0 -> {
                    close()
                }
                -1 -> {
                    dialogLeaderAlreadyAdded()
                }
                else -> {
                    dialogLeaderNotFound()
                }
            }
        }
        binding.leaderProofCancel.setOnClickListener {
            //TODO dialog czy na pewno chcesz anulowwać

            //jeśli tak to:
            close()

            //jeśli nie to :
            return@setOnClickListener
        }

    }

    private fun close() {
        if (new) {
            activity?.supportFragmentManager?.popBackStack("NewRoute", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_save_route,
                    NewRouteFragment(mViewModel.route.value!!.id)
                )
                ?.addToBackStack(null)
                ?.commit()
        } else {
            activity?.supportFragmentManager?.popBackStack("AddProof", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_save_route,
                    EditProofsFragment(mViewModel.route.value!!.id.toLong())
                )
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    private fun checkLeader() {
        CoroutineScope(IO).launch {
            delay(10)
            CoroutineScope(Main).launch {
                val text = binding.leaderId.text.toString()
                if (text != "") {
                    val leaderName = mViewModel.getLeaderName(text.toLong())
                    binding.leaderName.text = leaderName
                }
                checkLeader()
            }
        }
    }

    private fun dialogLeaderAlreadyAdded() {
        //TODO dialog że przodownik już jest dodany do tej trasy
    }

    private fun dialogLeaderNotFound() {
        //TODO dialog że przodownik w bazie nie istnieje
    }

}