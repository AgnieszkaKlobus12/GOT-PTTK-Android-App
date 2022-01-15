package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.poapp.model.entity.Leader

@Dao
interface LeaderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(leader: Leader): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(leader: Leader)

    @Query("select * from Przodownicy where nrLegitymacji = :leaderId")
    fun getLeader(leaderId: Long): Leader

    @Delete
    fun delete(tourist: Leader)

    @Query("delete from Przodownicy")
    fun deleteAll()

    @Query("select * from Przodownicy")
    fun getAll(): LiveData<List<Leader>>
}