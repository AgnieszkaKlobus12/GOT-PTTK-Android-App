package com.example.poapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.poapp.model.entities.OdcinekTrasy

@Dao
interface OdcinekTrasyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(odcinekT: OdcinekTrasy)

}