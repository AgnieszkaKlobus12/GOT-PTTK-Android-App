package com.example.poapp.view.turysta.trasy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.poapp.R

class NowaTrasaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nowa_trasa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val oficjalny = view.findViewById<Button>(R.id.oficjlany_button)
        val własny = view.findViewById<Button>(R.id.wlasny_button)

        view.findViewById<Button>(R.id.dodaj_odcinek_button).setOnClickListener {
            oficjalny.visibility = View.VISIBLE
            własny.visibility = View.VISIBLE
        }

        view.findViewById<Button>(R.id.dodaj_dowod_button).setOnClickListener{
            TODO()
        }

        view.findViewById<Button>(R.id.end_button).setOnClickListener {
            TODO()
        }


    }
}