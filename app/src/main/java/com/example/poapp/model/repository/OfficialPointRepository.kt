package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.OfficialPointDAO
import com.example.poapp.model.entity.OfficialPoint

class OfficialPointRepository(private val geoPointDAO: OfficialPointDAO) {
    fun insert(officialPoint: OfficialPoint): Long {
        return geoPointDAO.insert(officialPoint)
    }

    fun getAll(): LiveData<List<OfficialPoint>> {
        return geoPointDAO.getAll()
    }

    fun update(geoPoint: OfficialPoint) {
        geoPointDAO.update(geoPoint)
    }

    fun getOfficialPoint(id: Int): List<OfficialPoint> {
        return geoPointDAO.getOfficialPoint(id)
    }

    fun getOfficialPoint(name: String): List<OfficialPoint> {
        return geoPointDAO.getOfficialPoint(name)
    }
}