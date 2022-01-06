package com.example.poapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.poapp.model.entity.Turysta

@Dao
interface TouristDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(turysta: Turysta)

}