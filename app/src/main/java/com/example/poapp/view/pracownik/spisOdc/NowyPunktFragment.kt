package com.example.poapp.view.pracownik.spisOdc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.poapp.R
import com.example.poapp.model.entity.GrupaGorska
import com.example.poapp.model.entity.PasmoGorskie
import com.example.poapp.model.entity.PunktOficjalny
import com.example.poapp.viewModel.OdcinekOficjalnyViewModel


class NowyPunktFragment(private val punktId: Int) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nowy_punkt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mViewModel = ViewModelProviders.of(this)[OdcinekOficjalnyViewModel::class.java]

        val nazwa: EditText = view.findViewById(R.id.nazwa_punkt)
        val dlugosc: EditText = view.findViewById(R.id.dlugosc_geo_punky)
        val szerokosc: EditText = view.findViewById(R.id.szerokosc_geo_punky)
        val grupaGorska: EditText = view.findViewById(R.id.grupa_gor_punkt)
        val pasmoGorskie: EditText = view.findViewById(R.id.pasmo_gor_punkt)

        var punkt = PunktOficjalny(0, "", 0.0, 0.0, 0)

        if (punktId != 0) {
            var punktL = emptyList<PunktOficjalny>()
            mViewModel.getPunktOficjalny(id).observe(viewLifecycleOwner, { punkty ->
                punkty?.let { punktL = it }
            })
            punkt = punktL[0]
            var pasmoL = emptyList<PasmoGorskie>()
            mViewModel.getPasmo(punkt.FKpasmoGorskie).observe(viewLifecycleOwner, { pasma ->
                pasma?.let { pasmoL = it }
            })
            pasmoGorskie.setText(pasmoL[0].nazwa)
            var grupaL = emptyList<GrupaGorska>()
            mViewModel.getGrupa(pasmoL[0].FKgrupaGorska).observe(viewLifecycleOwner, { grupty ->
                grupty?.let { grupaL = it }
            })
            grupaGorska.setText(grupaL[0].nazwa)
        }

        nazwa.setText(punkt.nazwa)
        dlugosc.setText(punkt.dlugoscGeo.toString())
        szerokosc.setText((punkt.szerokoscGeo.toString()))


        view.findViewById<Button>(R.id.punkt_save).setOnClickListener {
            var punktL = emptyList<PunktOficjalny>()
            mViewModel.getPunktOficjalny(nazwa.text.toString())
                .observe(viewLifecycleOwner, { punkty ->
                    punkty?.let { punktL = it }
                })
            if (punktL.isNotEmpty()) {
                punkt = punktL[0]
            }
        }
    }
}