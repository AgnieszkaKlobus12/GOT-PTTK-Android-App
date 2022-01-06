package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.GeoPointDAO
import com.example.poapp.model.entity.GeoPoint

class GeoPointRepository(private val geoPointDAO: GeoPointDAO) {
    fun insert(geoPoint: GeoPoint) {
        geoPointDAO.insert(geoPoint)
    }

    fun getAll(): LiveData<List<GeoPoint>> {
        return geoPointDAO.getAll()
    }

    fun update(geoPoint: GeoPoint) {
        geoPointDAO.update(geoPoint)
    }

    fun getGeoPoint(id: Int): LiveData<List<GeoPoint>> {
        return geoPointDAO.getGeoPoint(id)
    }

    fun getGeoPoint(name: String): LiveData<List<GeoPoint>> {
        return geoPointDAO.getGeoPoint(name)
    }
}