package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.MountainPassOfficial

@Dao
interface MountainPassOfficialDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mountainPassOfficial: MountainPassOfficial)

    @Query("select * from OdcinkiOficjalne")
    fun getAll(): LiveData<List<MountainPassOfficial>>

    @Query("select * from OdcinkiOficjalne where id = :passId")
    fun getMountainPass(passId: Int): List<MountainPassOfficial>
}