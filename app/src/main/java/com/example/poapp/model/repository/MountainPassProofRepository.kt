package com.example.poapp.model.repository

import com.example.poapp.model.dao.MountainPassProofDAO
import com.example.poapp.model.entity.MountainPassProof

class MountainPassProofRepository(private val mountainPassProofDAO: MountainPassProofDAO) {

    fun proofsFor(routeSectionId: Long): List<MountainPassProof> {
        return mountainPassProofDAO.proofsFor(routeSectionId)
    }
}