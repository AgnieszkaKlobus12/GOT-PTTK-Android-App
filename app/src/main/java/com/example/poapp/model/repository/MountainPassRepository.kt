package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.MountainPassDAO
import com.example.poapp.model.entity.MountainPassOfficial

class MountainPassRepository(private val mountainPassDAO: MountainPassDAO) {


    fun insert(mountainPass: MountainPassOfficial) {
        mountainPassDAO.insert(mountainPass)
    }

    fun getAll(): LiveData<List<MountainPassOfficial>> {
        return mountainPassDAO.getAll()
    }

    fun geMountainPass(id: Int): List<MountainPassOfficial> {
        return mountainPassDAO.getMountainPass(id)
    }
}