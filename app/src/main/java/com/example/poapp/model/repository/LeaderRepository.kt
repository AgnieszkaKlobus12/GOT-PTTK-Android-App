package com.example.poapp.model.repository

import com.example.poapp.model.dao.LeaderDAO
import com.example.poapp.model.entity.Leader

class LeaderRepository(private val leaderDAO: LeaderDAO) {

    fun getLeader(leaderId: Long): Leader {
        return leaderDAO.getLeader(leaderId)
    }
}