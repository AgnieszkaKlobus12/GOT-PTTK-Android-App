package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.MountainGroupDAO
import com.example.poapp.model.entity.MountainGroup

class MountainGroupRepository(private val mountainGroupDAO: MountainGroupDAO) {

    fun insert(mountainGroup: MountainGroup) {
        return mountainGroupDAO.insert(mountainGroup)
    }

    fun getAll(): LiveData<List<MountainGroup>> {
        return mountainGroupDAO.getAll()
    }

    fun getMountainGroup(name: String): List<MountainGroup> {
        return mountainGroupDAO.getMountainGroup(name)
    }

    fun getMountainGroup(id: Int): List<MountainGroup> {
        return mountainGroupDAO.getMountainGroup(id)
    }
}