package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.PunktOficjalny

@Dao
interface PunktOficjalnyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(punktO: PunktOficjalny)

    @Query("select * from PunktyOficjalne")
    fun getAllPunkty(): LiveData<List<PunktOficjalny>>

    @Query("select * from PunktyOficjalne where id = :punktId")
    fun getPunktOficjalny(punktId: Int): LiveData<List<PunktOficjalny>>

    @Query("select * from PunktyOficjalne where nazwa = :nazwa")
    fun getPunktOficjalny(nazwa: String): LiveData<List<PunktOficjalny>>
}