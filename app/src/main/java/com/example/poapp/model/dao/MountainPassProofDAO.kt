package com.example.poapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.MountainPassProof

@Dao
interface MountainPassProofDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mountainPassProof: MountainPassProof)

    @Query("delete from DowodyOdcinkow")
    fun deleteAll()

}