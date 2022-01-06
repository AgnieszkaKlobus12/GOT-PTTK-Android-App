package com.example.poapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.poapp.model.entity.OdcinekTrasy

@Dao
interface RouteSectionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(odcinekT: OdcinekTrasy)

}