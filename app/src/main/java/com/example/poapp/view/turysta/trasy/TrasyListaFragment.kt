package com.example.poapp.view.turysta.trasy

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
import com.example.poapp.model.repository.TrasaRepository

class TrasyListaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trasy_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val list = view.findViewById<RecyclerView>(R.id.list)
        list.layoutManager = LinearLayoutManager(context)
        Log.e("tag2", "Done")
        val trasaRep = TrasaRepository(requireActivity().application, 1)
        list.adapter = trasaRep.getAllTrasyForUser(1).value?.let { TrasaItemAdapter(it) }
        //list.adapter = TrasaItemAdapter(listOf(Trasa(1, 1, "2021-11-12", "oczekująca", 20)))


        view.findViewById<Button>(R.id.dodaj_trase).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_trasy, NowaTrasaFragment())?.addToBackStack(null)
                ?.commit()
        }
    }
}