package com.example.poapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.entity.MountainGroup
import com.example.poapp.model.entity.MountainPassOfficial
import com.example.poapp.model.entity.MountainRange
import com.example.poapp.model.entity.GeoPoint
import com.example.poapp.model.repository.MountainGroupRepository
import com.example.poapp.model.repository.OficialSectionRepository
import com.example.poapp.model.repository.MountainRangeRepository
import com.example.poapp.model.repository.GeoPointRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//holds data for Adding/Editing MountainPass - including GeoPoints and other helping fragments
class MountainPassOfficialViewModel(application: Application) : AndroidViewModel(application) {

    private val odcinekRepository: OficialSectionRepository
    private val punktRepository: GeoPointRepository
    private val mountainGroupRepository: MountainGroupRepository
    private val mountainRangeRepository: MountainRangeRepository
    var mountainPassOfficial = MountainPassOfficial(0, "-", 0, 0, 0, 0, 0, "aktywny")

    init {
        val database = AppDatabase.getInstance(application)
        odcinekRepository = OficialSectionRepository(database.odcinekOficjlanyDao())
        punktRepository = GeoPointRepository(database.punktOficjalnyDao())
        mountainGroupRepository = MountainGroupRepository(database.grupaGorskaDao())
        mountainRangeRepository = MountainRangeRepository(database.pasmoGorskieDao())
    }

    fun setMountainPass(mountainPassOfficial: MountainPassOfficial) {
        this.mountainPassOfficial = mountainPassOfficial
    }

    fun addOdcinekOficjalny(odcinek: MountainPassOfficial) {
        viewModelScope.launch(Dispatchers.IO) {
            odcinekRepository.insert(odcinek)
        }
    }

    fun getAll(): LiveData<List<MountainPassOfficial>> {
        return odcinekRepository.getAllOdcinki()
    }

    fun getAllMountainRanges(): LiveData<List<MountainRange>> {
        return mountainRangeRepository.getAll()
    }

    fun getMountainPassOfficial(id: Int): LiveData<List<MountainPassOfficial>> {
        return odcinekRepository.getOdcinek(id)
    }

    fun addGeoPoint(point: GeoPoint) {
        viewModelScope.launch(Dispatchers.IO) {
            punktRepository.insert(point)
        }
    }

    fun updateGeoPoint(point: GeoPoint) {
        viewModelScope.launch(Dispatchers.IO) {
            punktRepository.update(point)
        }
    }

    fun addMountainRange(mountainRange: MountainRange) {
        viewModelScope.launch(Dispatchers.IO) {
            mountainRangeRepository.insert(mountainRange)
        }
    }

    fun addMountainGroup(mountainGroup: MountainGroup): Long {
        var id = Long.MIN_VALUE
        viewModelScope.launch(Dispatchers.IO) {
            id = mountainGroupRepository.insert(mountainGroup)
        }
        return id
    }

    fun getOfficialPoint(id: Int): LiveData<List<GeoPoint>> {
        return punktRepository.getGeoPoint(id)
    }

    fun getOfficialPoint(name: String): LiveData<List<GeoPoint>> {
        return punktRepository.getGeoPoint(name)
    }

    fun getMountainGroup(id: Int): LiveData<List<MountainGroup>> {
        return mountainGroupRepository.getMountainGroup(id.toLong())
    }

    fun getMountainGroup(name: String): LiveData<List<MountainGroup>> {
        return mountainGroupRepository.getMountainGroup(name)
    }

    fun getMountainRange(id: Int): LiveData<List<MountainRange>> {
        return mountainRangeRepository.getMountainRange(id)
    }

    fun getMountainRange(name: String): LiveData<List<MountainRange>> {
        return mountainRangeRepository.getMountainRange(name)
    }
}