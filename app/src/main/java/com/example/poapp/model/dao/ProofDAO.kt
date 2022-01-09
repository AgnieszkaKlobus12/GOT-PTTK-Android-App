package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.poapp.model.entity.Proof

@Dao
interface ProofDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(proof: Proof): Long

    @Query("delete from Dowody")
    fun deleteAll()

    @Query("select * from Dowody")
    fun getAll(): LiveData<List<Proof>>

}