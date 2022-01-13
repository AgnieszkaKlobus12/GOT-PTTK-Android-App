package com.example.poapp.view.tourist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.MountainPassOfficial
import com.example.poapp.model.entity.MountainPassUser
import com.example.poapp.viewModel.MountainPassListViewModel

class PickMountainPassFragment(
    private val ifOfficial: Boolean
) : Fragment() {

    private val mViewModel: MountainPassListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pick_mountain_pass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = view.findViewById<RecyclerView>(R.id.mountain_passes_list_pick)
        if (ifOfficial) {
            var allPasses = emptyList<MountainPassOfficial>()
            mViewModel.getAllOfficialPasses().observe(viewLifecycleOwner, { passes ->
                passes?.let { allPasses = it }
                list.adapter = MountainPassPickAdapter(allPasses, mViewModel)
            })
        } else {
            var allPasses = emptyList<MountainPassUser>()
            mViewModel.getAllUserPasses().observe(viewLifecycleOwner, { passes ->
                passes?.let { allPasses = it }
                list.adapter = MountainPassPickAdapter(allPasses, mViewModel)
            })
        }
    }

}