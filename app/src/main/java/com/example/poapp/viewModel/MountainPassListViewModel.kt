package com.example.poapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.entity.MountainPassOfficial
import com.example.poapp.model.entity.MountainPassUser
import com.example.poapp.model.entity.OfficialPoint
import com.example.poapp.model.entity.UserPoint
import com.example.poapp.model.repository.*

class MountainPassListViewModel(application: Application) : AndroidViewModel(application) {

    private val mountainPassOfficialRepository: MountainPassOfficialRepository
    private val officialPointRepository: OfficialPointRepository
    private val mountainPassUserRepository: MountainPassUserRepository
    private val userPointRepository: UserPointRepository
    private val mountainGroupRepository: MountainGroupRepository
    private val mountainRangeRepository: MountainRangeRepository

    init {
        val database = AppDatabase.getInstance(application)
        mountainPassOfficialRepository =
            MountainPassOfficialRepository(database.mountainPassOfficialDAO())
        mountainPassUserRepository = MountainPassUserRepository(database.mountainPassUserDAO())
        officialPointRepository = OfficialPointRepository(database.officialPointDAO())
        mountainGroupRepository = MountainGroupRepository(database.mountainGroupDAO())
        mountainRangeRepository = MountainRangeRepository(database.mountainRangeDAO())
        userPointRepository = UserPointRepository(database.userPointDAO())
    }

    fun getAllOfficialPasses(): LiveData<List<MountainPassOfficial>> {
        return mountainPassOfficialRepository.getAll()
    }

    fun getOfficialPoint(id: Int): OfficialPoint {
        return officialPointRepository.getOfficialPoint(id)[0]
    }

    fun getAllUserPasses(): LiveData<List<MountainPassUser>> {
        return mountainPassUserRepository.getAll()
    }

    fun getUserPoint(id: Int): UserPoint {
        return userPointRepository.getUserPoint(id)[0]
    }

}