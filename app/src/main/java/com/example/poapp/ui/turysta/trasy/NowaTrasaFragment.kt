package com.example.poapp.ui.turysta.trasy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.poapp.R

class NowaTrasaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_nowa_trasa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val oficjalny = view.findViewById<Button>(R.id.oficjalny_button)
        val wlasny = view.findViewById<Button>(R.id.wlasny_button)

        view.findViewById<Button>(R.id.dodaj_odcinek_button).setOnClickListener {
            oficjalny.visibility = View.VISIBLE
            wlasny.visibility = View.VISIBLE
        }

        view.findViewById<Button>(R.id.dodaj_dowod_button).setOnClickListener{
            Toast.makeText(activity, R.string.extension_point_label, Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.zakoncz_button).setOnClickListener {
            TODO()
        }


    }
}