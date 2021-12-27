package com.example.poapp.model.dao

import androidx.room.*
import com.example.poapp.model.entities.Turysta
import com.example.poapp.model.entities.Uzytkownik

@Dao
interface TurystaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(turysta: Turysta)

}