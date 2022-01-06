package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.Trasa

@Dao
interface RouteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trasa: Trasa)

    @Query("select * from Trasy where FKturysta = :FKturysta")
    fun getAllTrasy(FKturysta: Int): LiveData<List<Trasa>>
}