package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.OdcinekOficjalny

@Dao
interface OdcinekOficjalnyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(odcinekO: OdcinekOficjalny)

    @Query("select * from OdcinkiOficjalne")
    fun getAllOdcinki(): LiveData<List<OdcinekOficjalny>>

    @Query("select * from OdcinkiOficjalne where id = :odcinekId")
    fun getOdcinekOficjalny(odcinekId: Int): LiveData<List<OdcinekOficjalny>>
}