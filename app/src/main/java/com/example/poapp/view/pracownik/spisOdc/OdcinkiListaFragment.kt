package com.example.poapp.view.pracownik.spisOdc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poapp.R
import com.example.poapp.model.entity.OdcinekOficjalny
import com.example.poapp.viewModel.OdcinekOficjalnyViewModel

class OdcinkiListaFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_odcinki_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mViewModel = ViewModelProviders.of(this)[OdcinekOficjalnyViewModel::class.java]

        val list = view.findViewById<RecyclerView>(R.id.listOdc)
        list.layoutManager = LinearLayoutManager(context)
        var allOdcinki = emptyList<OdcinekOficjalny>()
        mViewModel.getAll().observe(viewLifecycleOwner, Observer { odcinki ->
            odcinki?.let { allOdcinki = it }
        })
        list.adapter = OdcinekItemAdapter(
            allOdcinki
        )

        view.findViewById<Button>(R.id.dodaj_odcinek).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_spis_odc, NowyOdcinekFragment(0))
                ?.addToBackStack(null)
                ?.commit()
        }

    }
}