package com.example.poapp.view.tourist.route

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.poapp.R
import com.example.poapp.databinding.FragmentRouteDetailsBinding
import com.example.poapp.view.tourist.proof.EditProofsFragment
import com.example.poapp.viewModel.NewRouteViewModel

class RouteDetailsFragment : Fragment() {

    private var _binding: FragmentRouteDetailsBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: NewRouteViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        FragmentRouteDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allRouteSection = mViewModel.getAllRouteSections()
        binding.routeSectionList.adapter = RouteSectionAdapter(activity as Context, allRouteSection, mViewModel)

        binding.editProofs.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_save_route,
                    EditProofsFragment(mViewModel.route.value!!.id.toLong())
                )
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.editRoute.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.not_implemented_label), Toast.LENGTH_SHORT).show()
        }
        binding.deleteRoute.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.not_implemented_label), Toast.LENGTH_SHORT).show()
        }
        binding.sendToLeader.setOnClickListener {
            //TODO - for last UC
        }
    }
}