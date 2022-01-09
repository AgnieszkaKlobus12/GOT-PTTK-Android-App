package com.example.poapp.view.tourist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.RouteSection
import com.example.poapp.viewModel.NewRouteViewModel

class NewRouteFragment : Fragment() {

    private val mViewModel: NewRouteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val official = view.findViewById<Button>(R.id.official_button)
        val own = view.findViewById<Button>(R.id.own_button)

        view.findViewById<Button>(R.id.add_mountain_pass_button).setOnClickListener {
            official.visibility = View.VISIBLE
            own.visibility = View.VISIBLE
        }

        view.findViewById<Button>(R.id.add_proof_button).setOnClickListener {
            Toast.makeText(activity, R.string.extension_point_label, Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.end_button).setOnClickListener {
            TODO()
        }

        val list = view.findViewById<RecyclerView>(R.id.route_section_list)
        list.layoutManager = LinearLayoutManager(context)
        var allRouteSection = emptyList<RouteSection>()
//        mViewModel.getAllRouteSections().observe(viewLifecycleOwner, { passes ->
//            passes?.let { allRouteSection = it }
//            list.adapter = RouteAdapter(
//                allRouteSection, mViewModel, object : OnRouteClickedListener {
//                    override fun onItemClick(item: Route) {
////                        activity?.supportFragmentManager?.beginTransaction()
////                            ?.replace(
////                                R.id.nav_host_fragment_activity_mountain_passes_list,
////                                MountainPassDetailsFragment(item.id)
////                            )
////                            ?.addToBackStack(null)
////                            ?.commit()
////                        TODO add on click - for add proof use case
//                    }
//                }
//            )
//        })

    }
}