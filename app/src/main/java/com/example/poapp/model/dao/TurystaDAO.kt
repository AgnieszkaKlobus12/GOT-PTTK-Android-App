package com.example.poapp.model.dao

import androidx.room.*
import com.example.poapp.model.entity.Turysta

@Dao
interface TurystaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(turysta: Turysta)

}