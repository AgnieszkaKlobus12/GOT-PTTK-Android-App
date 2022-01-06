package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.MountainRangeDAO
import com.example.poapp.model.entity.MountainRange

class MountainRangeRepository(private val mountainRangeDAO: MountainRangeDAO) {

    fun insert(mountainRange: MountainRange) {
        mountainRangeDAO.insert(mountainRange)
    }

    fun getAll(): LiveData<List<MountainRange>> {
        return mountainRangeDAO.getAll()
    }

    fun getMountainRange(name: String): LiveData<List<MountainRange>> {
        return mountainRangeDAO.getMountainRange(name)
    }

    fun getMountainRange(id: Int): LiveData<List<MountainRange>> {
        return mountainRangeDAO.getMountainRange(id)
    }
}