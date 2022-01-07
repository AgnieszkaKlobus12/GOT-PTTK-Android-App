package com.example.poapp.view.tourist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.poapp.R

class NewRouteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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


    }
}