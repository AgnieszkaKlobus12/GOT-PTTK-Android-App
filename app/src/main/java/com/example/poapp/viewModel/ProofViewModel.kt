package com.example.poapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.repository.MountainPassProofRepository

class ProofViewModel(application: Application) : AndroidViewModel(application) {
    private var routeId = 0.toLong()
    private val mountainPassProofRepository: MountainPassProofRepository

    init {
        val database = AppDatabase.getInstance(application)
        mountainPassProofRepository = MountainPassProofRepository(database.mountainPassProofDAO())
    }

    fun setRoute(routeId: Long) {
        this.routeId = routeId
    }

    fun saveLeaderProof(leaderID: Long) {
        TODO()
    }
}