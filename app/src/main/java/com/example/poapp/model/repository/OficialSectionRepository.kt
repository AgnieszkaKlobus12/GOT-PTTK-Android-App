package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.OdcinekOficjalnyDAO
import com.example.poapp.model.entity.MountainPassOfficial

class OficialSectionRepository(private val odcinekDao: OdcinekOficjalnyDAO) {
    

    fun insert(odcinek: MountainPassOfficial) {
        odcinekDao.insert(odcinek)
    }

    fun getAllOdcinki(): LiveData<List<MountainPassOfficial>> {
        return odcinekDao.getAllOdcinki()
    }

    fun getOdcinek(id: Int): LiveData<List<MountainPassOfficial>> {
        return odcinekDao.getOdcinekOficjalny(id)
    }
}