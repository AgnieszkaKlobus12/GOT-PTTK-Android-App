package com.example.poapp.view.tourist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.poapp.R
import com.example.poapp.viewModel.NewRouteViewModel

class SaveRouteFragment : Fragment() {

    private val mViewModel: NewRouteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_save_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val points = mViewModel.updateRoutePoints()
        var date = ""
        view.findViewById<CalendarView>(R.id.calendarView).setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            var monthString = (month + 1).toString()
            if (month + 1 < 10) {
                monthString = "0${month + 1}"
            }
            var dayString = dayOfMonth.toString()
            if (dayOfMonth < 10) {
                dayString = "0$dayOfMonth"
            }
            date = "$year-$monthString-$dayString"
        }
        view.findViewById<TextView>(R.id.points_sum).text = points.toString()
        view.findViewById<Button>(R.id.cancel_save_route).setOnClickListener {
            //TODO - remove route
        }
        view.findViewById<Button>(R.id.save_route_btn).setOnClickListener {
            mViewModel.route.value!!.dataPrzejscia = date
            mViewModel.updateRoute()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_save_route,
                    RouteListFragment()
                )
                ?.addToBackStack(null)
                ?.commit()
        }
    }

}