package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.PunktOficjalnyDAO
import com.example.poapp.model.entity.PunktOficjalny

class PunktOficjalnyRepository(private val punktDao: PunktOficjalnyDAO) {
    fun insert(punkt: PunktOficjalny) {
        punktDao.insert(punkt)
    }

    fun getAllPunkty(): LiveData<List<PunktOficjalny>> {
        return punktDao.getAllPunkty()
    }

    fun getPunkt(id: Int): LiveData<List<PunktOficjalny>> {
        return punktDao.getPunktOficjalny(id)
    }

    fun getPunkt(nazwa: String): LiveData<List<PunktOficjalny>> {
        return punktDao.getPunktOficjalny(nazwa)
    }
}