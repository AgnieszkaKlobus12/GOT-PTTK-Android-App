package com.example.poapp.view.tourist.proof

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.viewModel.RouteViewModel


class AddImageSectionsListFragment : Fragment() {

    private val mViewModel: RouteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_image_sections_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.route_section_pick_list).adapter =
            RouteSectionsPickAdapter(mViewModel.getAllRouteSections(), mViewModel)
        view.findViewById<Button>(R.id.pick_picture_button).setOnClickListener {
            //todo
        }
    }

}