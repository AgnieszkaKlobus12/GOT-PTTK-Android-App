package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.MountainGroup

@Dao
interface MountainGroupDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mountainGroup: MountainGroup): Long

    @Query("select * from GrupyGorskie")
    fun getAll(): LiveData<List<MountainGroup>>

    @Query("select * from GrupyGorskie where nazwa = :nameMG")
    fun getMountainGroup(nameMG: String): LiveData<List<MountainGroup>>

    @Query("select * from GrupyGorskie where id = :idMG")
    fun getMountainGroup(idMG: Long): LiveData<List<MountainGroup>>
}