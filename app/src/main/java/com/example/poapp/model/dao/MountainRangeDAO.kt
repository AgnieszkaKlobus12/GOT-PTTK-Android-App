package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.MountainRange

@Dao
interface MountainRangeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mountainRange: MountainRange)

    @Query("select * from PasmaGorskie")
    fun getAll(): LiveData<List<MountainRange>>

    @Query("select * from PasmaGorskie where nazwa = :MRname")
    fun getMountainRange(MRname: String): List<MountainRange>

    @Query("select * from PasmaGorskie where id = :MRid")
    fun getMountainRange(MRid: Int): List<MountainRange>
}