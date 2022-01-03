package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.GrupaGorskaDAO
import com.example.poapp.model.entity.GrupaGorska

class GrupaGroskaRepository(private val grupaDAO: GrupaGorskaDAO) {

    fun insert(grupa: GrupaGorska) {
        grupaDAO.insert(grupa)
    }

    fun getAllGrupy(): LiveData<List<GrupaGorska>> {
        return grupaDAO.getAllGrupy()
    }

    fun getGrupa(nazwa: String): LiveData<List<GrupaGorska>> {
        return grupaDAO.getGrupaGorska(nazwa)
    }

    fun getGrupa(nazwa: Int): LiveData<List<GrupaGorska>> {
        return grupaDAO.getGrupaGorska(nazwa)
    }
}