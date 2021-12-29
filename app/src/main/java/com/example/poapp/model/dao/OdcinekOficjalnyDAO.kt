package com.example.poapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.poapp.model.entities.OdcinekOficjalny
import com.example.poapp.model.entities.Turysta

@Dao
interface OdcinekOficjalnyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(odcinekO: OdcinekOficjalny)
}