package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.OdcinekOficjalnyDAO
import com.example.poapp.model.entity.OdcinekOficjalny

class OdcinekOficjalnyRepository(private val odcinekDao: OdcinekOficjalnyDAO) {

//    private var odcinekDao: OdcinekOficjalnyDAO
//    private var allOdcinek: LiveData<List<OdcinekOficjalny>>
//
//    private val database = AppDatabase.getInstance(application)
//
//    init {
//        odcinekDao = database.odcinekOficjlanyDao()
//        allOdcinek = odcinekDao.getAllOdcinki()
//    }

    fun insert(odcinek: OdcinekOficjalny) {
        odcinekDao.insert(odcinek)
    }

    fun getAllOdcinki(): LiveData<List<OdcinekOficjalny>> {
        return odcinekDao.getAllOdcinki()
    }

    fun getOdcinek(id: Int): LiveData<List<OdcinekOficjalny>>{
        return odcinekDao.getOdcinekOficjalny(id)
    }
}