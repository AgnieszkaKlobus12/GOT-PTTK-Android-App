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
import com.example.poapp.model.entity.Route
import com.example.poapp.model.entity.RouteSection
import com.example.poapp.viewModel.NewRouteViewModel

class NewRouteFragment(private val route: Route?) : Fragment() {

    private val mViewModel: NewRouteViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (route != null) {
            mViewModel.setRoute(route)
        }
    }

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
            if (mViewModel.getAllRouteSections().value?.isEmpty() == true) {
                showAddRouteSectionDialog()
                return@setOnClickListener
            }
            Toast.makeText(activity, R.string.extension_point_label, Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.end_button).setOnClickListener {
            if (mViewModel.getAllRouteSections().value?.isEmpty() == true) {
                showAddRouteSectionDialog()
                return@setOnClickListener
            }
            TODO()
        }

        official.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_save_route,
                    PickMountainPassFragment(true)
                )
                ?.addToBackStack(null)
                ?.commit()
        }
        own.setOnClickListener {
            TODO()
        }


        val list = view.findViewById<RecyclerView>(R.id.route_section_list)
        list.layoutManager = LinearLayoutManager(context)
        var allRouteSection = emptyList<RouteSection>()
        mViewModel.getAllRouteSections().observe(viewLifecycleOwner, { passes ->
            passes?.let { allRouteSection = it }
            list.adapter = RouteSectionAdapter(
                allRouteSection, mViewModel
            )
        })
    }

    private fun showAddRouteSectionDialog() {
        //TODO dialog - "dodaj przynajmniej jeden odcinek
    }
}