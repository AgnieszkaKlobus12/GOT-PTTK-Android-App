package com.example.poapp.view.pracownik.spisOdc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.poapp.R
import com.example.poapp.model.entity.OdcinekOficjalny
import com.example.poapp.viewModel.OdcinekOficjalnyViewModel

class NowyOdcinekFragment(private val odcinekId: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nowy_odcinek, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mViewModel = ViewModelProviders.of(this)[OdcinekOficjalnyViewModel::class.java]

        var odcinekOficjalny = OdcinekOficjalny(0, "-", 0, 0, 0, 0, 0, "aktywny")
        if (odcinekId != 0) {
            var odcinekL = emptyList<OdcinekOficjalny>()
            //mViewModel.addOdcinekOficjalny(OdcinekOficjalny(0, "test", 10, 2,3 ,3, 2, "Test"))

            mViewModel.getOdcinekOficjalny(odcinekId)
                .observe(viewLifecycleOwner, Observer { odcinki ->
                    odcinki?.let { odcinekL = it }
                })

            odcinekOficjalny = odcinekL[0]
        }

        val poczatek: TextView = view.findViewById(R.id.nowy_text_poczatek)
        val koniec: TextView = view.findViewById(R.id.nowy_text_koniec)
        val punkty: TextView = view.findViewById(R.id.nowy_text_punkty)
        val przez: TextView = view.findViewById(R.id.nowy_text_przez)
        val nazwa: TextView = view.findViewById(R.id.nowy_text_nazwa)
        val status: TextView = view.findViewById(R.id.nowy_text_status)
        poczatek.text = odcinekOficjalny.FKpunktPoczatkowy.toString()
        koniec.text = odcinekOficjalny.FKpunktKoncowy.toString()
        punkty.text = odcinekOficjalny.punkty.toString()
        przez.text = odcinekOficjalny.FKpunktPosredni.toString()
        nazwa.text = odcinekOficjalny.nazwa
        status.text = odcinekOficjalny.status

        view.findViewById<Button>(R.id.edytujPoczatek).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_spis_odc,
                    NowyPunktFragment(odcinekOficjalny.FKpunktPoczatkowy)
                )
                ?.addToBackStack(null)
                ?.commit()
        }
        view.findViewById<Button>(R.id.edytujKoniec).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.nav_host_fragment_activity_spis_odc,
                    NowyPunktFragment(odcinekOficjalny.FKpunktKoncowy)
                )
                ?.addToBackStack(null)
                ?.commit()
        }
        view.findViewById<Button>(R.id.edytujPunkty).setOnClickListener {

        }
        view.findViewById<Button>(R.id.edytujPrzez).setOnClickListener {
            var id = 0
            if (odcinekOficjalny.FKpunktPosredni != null) {
                id = odcinekOficjalny.FKpunktPosredni!!
            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_spis_odc, NowyPunktFragment(id))
                ?.addToBackStack(null)
                ?.commit()
        }
        view.findViewById<Button>(R.id.edytujNazwa).setOnClickListener {

        }
        view.findViewById<Button>(R.id.edytujStatus).setOnClickListener {

        }

        view.findViewById<Button>(R.id.anuluj_nOd).setOnClickListener {

        }
        view.findViewById<Button>(R.id.zapisz_nOd).setOnClickListener {

        }
    }

}