package com.example.poapp.view.tourist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R

class RouteListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_route_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val list = view.findViewById<RecyclerView>(R.id.last_routes_list)
        list.layoutManager = LinearLayoutManager(context)
        Log.e("tag2", "Done")
//        val trasaRep = RouteRepository(requireActivity().application, 1)
//        list.adapter = trasaRep.getAllForUser(1).value?.let { TrasaItemAdapter(it) }
        //list.adapter = TrasaItemAdapter(listOf(Trasa(1, 1, "2021-11-12", "oczekująca", 20)))


        view.findViewById<Button>(R.id.add_route_button).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_save_route, NewRouteFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}