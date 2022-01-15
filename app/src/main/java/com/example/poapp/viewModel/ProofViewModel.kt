package com.example.poapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.entity.Leader
import com.example.poapp.model.entity.MountainPassProof
import com.example.poapp.model.entity.Proof
import com.example.poapp.model.entity.RouteSection
import com.example.poapp.model.repository.LeaderRepository
import com.example.poapp.model.repository.MountainPassProofRepository
import com.example.poapp.model.repository.ProofRepository
import com.example.poapp.model.repository.RouteSectionRepository

class ProofViewModel(application: Application) : AndroidViewModel(application) {
    private var routeId = 0.toLong()
    private val mountainPassProofRepository: MountainPassProofRepository
    private val routeSectionRepository: RouteSectionRepository
    private val proofRepository: ProofRepository
    private val leaderRepository: LeaderRepository

    init {
        val database = AppDatabase.getInstance(application)
        mountainPassProofRepository = MountainPassProofRepository(database.mountainPassProofDAO())
        routeSectionRepository = RouteSectionRepository(database.routeSectionDAO())
        proofRepository = ProofRepository(database.proofDAO())
        leaderRepository = LeaderRepository(database.leaderDAO())
    }

    fun setRoute(routeId: Long) {
        this.routeId = routeId
    }

    fun saveLeaderProof(leaderID: Long): Boolean {
        if (getLeaderForRoute(routeId) != null) {
            val proof = Proof(0, null, leaderID.toInt())
            val proofId = proofRepository.insert(proof)
            val sections = getRouteSectionsForRoute(routeId)
            for (section in sections) {
                val sectionProof = MountainPassProof(0, section.id, proofId.toInt())
                mountainPassProofRepository.insert(sectionProof)
            }
            return true
        }
        return false
    }

    fun getLeaderForRoute(routeId: Long): Long? {
        val proofs = mountainPassProofRepository.proofsFor(getRouteSectionsForRoute(routeId))
        for (proof in proofs) {
            val leader = proofRepository.getProof(proof.FKdowod.toLong()).FKprzodownik
            if (leader != null) {
                return leader.toLong()
            }
        }
        return null
    }

    fun getRouteSectionsForRoute(routeId: Long): List<RouteSection> {
        return routeSectionRepository.getRouteSectionForRoute(routeId)
    }

    fun getLeaderWithNumber(leaderID: Long): Leader {
        return leaderRepository.getLeader(leaderID)
    }
}