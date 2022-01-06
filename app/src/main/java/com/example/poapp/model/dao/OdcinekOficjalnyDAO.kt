package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.MountainPassOfficial

@Dao
interface OdcinekOficjalnyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(odcinekO: MountainPassOfficial)

    @Query("select * from OdcinkiOficjalne")
    fun getAllOdcinki(): LiveData<List<MountainPassOfficial>>

    @Query("select * from OdcinkiOficjalne where id = :odcinekId")
    fun getOdcinekOficjalny(odcinekId: Int): LiveData<List<MountainPassOfficial>>
}