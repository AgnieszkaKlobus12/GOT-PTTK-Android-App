package com.example.poapp.view.leader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.example.poapp.R
import com.example.poapp.databinding.FragmentRouteSectionDetailsBinding
import com.example.poapp.model.entity.RouteSection
import com.example.poapp.viewModel.ConfirmRouteViewModel

class RouteSectionDetailsFragment(private val routeSection: RouteSection) : Fragment() {

    private var _binding: FragmentRouteSectionDetailsBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: ConfirmRouteViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRouteSectionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sectionDetailsEndValue.text = mViewModel.getSectionEndName(routeSection)
        binding.sectionDetailsStartValue.text = mViewModel.getSectionStartName(routeSection)
        binding.sectionDetailsTimeValue.text = routeSection.czasPrzejscia.toString()
        binding.sectionDetailsThroughValue.text = mViewModel.getSectionThroughName(routeSection)
        binding.sectionDetailsNameValue.text = mViewModel.getSectionName(routeSection)
        binding.sectionDetailsPointsValue.text = mViewModel.getSectionPoints(routeSection).toString()

        val proof = mViewModel.getSectionProof(routeSection)
        when {
            proof == null -> {
                binding.sectionDetailsProofImage.visibility = View.GONE
                binding.sectionDetailsLeaderNameValue.visibility = View.GONE
                binding.sectionDetailsLeaderLabel.visibility = View.GONE
                binding.sectionDetailsLeaderIdLabel.visibility = View.GONE
                binding.sectionDetailsLeaderIdValue.visibility = View.GONE
            }
            proof.FKprzodownik != null -> {
                binding.sectionDetailsProofImage.visibility = View.GONE
                binding.sectionDetailsLeaderNameValue.text = mViewModel.getLeaderName(proof.FKprzodownik)
                binding.sectionDetailsLeaderIdValue.text = proof.FKprzodownik.toString()
            }
            else -> {
                binding.sectionDetailsLeaderNameValue.visibility = View.GONE
                binding.sectionDetailsLeaderLabel.visibility = View.GONE
                binding.sectionDetailsLeaderIdLabel.visibility = View.GONE
                binding.sectionDetailsLeaderIdValue.visibility = View.GONE
                binding.sectionDetailsProofImage.setImageBitmap(mViewModel.getImage(proof.zdjecie!!))
            }
        }
        binding.closeSectionDetails.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack("ConfirmRoute", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_confirm,
                    RouteSectionsListFragment()
                )
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.showSectionsOnMap.setOnClickListener {
//            val gmmIntentUri =
//                Uri.parse("google.navigation:q=${mViewModel.getRouteSectionMapStart()}i&mode=w")
//            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
//            mapIntent.setPackage("com.google.android.apps.maps")
//            startActivity(mapIntent)
        }
    }
}