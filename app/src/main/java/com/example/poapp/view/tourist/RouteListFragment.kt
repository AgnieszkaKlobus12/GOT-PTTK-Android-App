package com.example.poapp.view.tourist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.Route
import com.example.poapp.viewModel.NewRouteViewModel

class RouteListFragment : Fragment() {

    private val mViewModel: NewRouteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_route_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = view.findViewById<RecyclerView>(R.id.last_routes_list)
        list.layoutManager = LinearLayoutManager(context)
        var allRoutes = emptyList<Route>()
        mViewModel.getAllRoutes(1).observe(viewLifecycleOwner, { passes ->
            passes?.let { allRoutes = it }
            list.adapter = RouteAdapter(
                allRoutes, mViewModel, object : OnRouteClickedListener {
                    override fun onItemClick(item: Route) {
//                        activity?.supportFragmentManager?.beginTransaction()
//                            ?.replace(
//                                R.id.nav_host_fragment_activity_mountain_passes_list,
//                                MountainPassDetailsFragment(item.id)
//                            )
//                            ?.addToBackStack(null)
//                            ?.commit()
//                        TODO add on click - for add proof use case
                    }
                }
            )
        })

        view.findViewById<Button>(R.id.add_route_button).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_save_route, NewRouteFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}