package com.example.poapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.repository.MountainGroupRepository
import com.example.poapp.model.repository.MountainPassRepository
import com.example.poapp.model.repository.MountainRangeRepository
import com.example.poapp.model.repository.OfficialPointRepository

class MountainPassViewModel(application: Application) : AndroidViewModel(application) {

    private val mountainPassRepository: MountainPassRepository
    private val officialPointRepository: OfficialPointRepository
    private val mountainGroupRepository: MountainGroupRepository
    private val mountainRangeRepository: MountainRangeRepository

    init {
        val database = AppDatabase.getInstance(application)
        mountainPassRepository = MountainPassRepository(database.mountainPassOfficialDAO())
        officialPointRepository = OfficialPointRepository(database.officialPointDAO())
        mountainGroupRepository = MountainGroupRepository(database.mountainGroupDAO())
        mountainRangeRepository = MountainRangeRepository(database.mountainRangeDAO())
    }

}