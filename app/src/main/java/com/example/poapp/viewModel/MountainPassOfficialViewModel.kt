package com.example.poapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.entity.MountainGroup
import com.example.poapp.model.entity.MountainPassOfficial
import com.example.poapp.model.entity.MountainRange
import com.example.poapp.model.entity.OfficialPoint
import com.example.poapp.model.repository.MountainGroupRepository
import com.example.poapp.model.repository.MountainPassRepository
import com.example.poapp.model.repository.MountainRangeRepository
import com.example.poapp.model.repository.OfficialPointRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//holds data for Adding/Editing MountainPass - including GeoPoints and other helping fragments
class MountainPassOfficialViewModel(application: Application) : AndroidViewModel(application) {

    private val mountainPassRepository: MountainPassRepository
    private val officialPointRepository: OfficialPointRepository
    private val mountainGroupRepository: MountainGroupRepository
    private val mountainRangeRepository: MountainRangeRepository
    val mountainPassOfficial =
        MutableLiveData(MountainPassOfficial(0, "-", 0, 0, 0, 0, 0, "aktywny"))
//    var mountainPassOfficial = MountainPassOfficial(0, "-", 0, 0, 0, 0, 0, "aktywny")

    init {
        val database = AppDatabase.getInstance(application)
        mountainPassRepository = MountainPassRepository(database.mountainPassDAO())
        officialPointRepository = OfficialPointRepository(database.geoPointDAO())
        mountainGroupRepository = MountainGroupRepository(database.mountainGroupDAO())
        mountainRangeRepository = MountainRangeRepository(database.mountainRangeDAO())
    }

    fun setMountainPass(mountainPassOfficial: MountainPassOfficial) {
        this.mountainPassOfficial.value = mountainPassOfficial
    }

    fun setMountainPassStart(id: Int) {
        mountainPassOfficial.value!!.FKpunktPoczatkowy = id
    }

    fun addOdcinekOficjalny(odcinek: MountainPassOfficial) {
        viewModelScope.launch(Dispatchers.IO) {
            mountainPassRepository.insert(odcinek)
        }
    }

    fun getAll(): LiveData<List<MountainPassOfficial>> {
        return mountainPassRepository.getAll()
    }

    fun getAllMountainRanges(): LiveData<List<MountainRange>> {
        return mountainRangeRepository.getAll()
    }

    fun getMountainPassOfficial(id: Int): List<MountainPassOfficial> {
        return mountainPassRepository.geMountainPass(id)
    }

    fun addOfficialPoint(point: OfficialPoint): Long {
        return officialPointRepository.insert(point)
    }

    fun updateGeoPoint(point: OfficialPoint) {
        viewModelScope.launch(Dispatchers.IO) {
            officialPointRepository.update(point)
        }
    }

    fun addMountainRange(mountainRange: MountainRange) {
        viewModelScope.launch(Dispatchers.IO) {
            mountainRangeRepository.insert(mountainRange)
        }
    }

    fun addMountainGroup(mountainGroup: MountainGroup) {
        viewModelScope.launch(Dispatchers.IO) {
            mountainGroupRepository.insert(mountainGroup)
        }
    }

    fun getOfficialPoint(id: Int): List<OfficialPoint> {
        return officialPointRepository.getOfficialPoint(id)
    }

    fun getOfficialPoint(name: String): List<OfficialPoint> {
        return officialPointRepository.getOfficialPoint(name)
    }

    fun getMountainGroup(id: Int): List<MountainGroup> {
        return mountainGroupRepository.getMountainGroup(id.toLong())
    }

    fun getMountainGroup(name: String): List<MountainGroup> {
        return mountainGroupRepository.getMountainGroup(name)
    }

    fun getMountainRange(id: Int): List<MountainRange> {
        return mountainRangeRepository.getMountainRange(id)
    }

    fun getMountainRange(name: String): List<MountainRange> {
        return mountainRangeRepository.getMountainRange(name)
    }
}