package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.PasmoGorskieDAO
import com.example.poapp.model.entity.PasmoGorskie

class PasmoGorskieRepository(private val pasmoDAO: PasmoGorskieDAO) {

    fun insert(pasmoGorskie: PasmoGorskie) {
        pasmoDAO.insert(pasmoGorskie)
    }

    fun getAllPasma(): LiveData<List<PasmoGorskie>> {
        return pasmoDAO.getAllPasma()
    }

    fun getPasmo(nazwa: String): LiveData<List<PasmoGorskie>> {
        return pasmoDAO.getPasmo(nazwa)
    }

    fun getPasmo(idP: Int): LiveData<List<PasmoGorskie>> {
        return pasmoDAO.getPasmoGorskie(idP)
    }
}