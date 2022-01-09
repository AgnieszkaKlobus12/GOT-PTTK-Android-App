package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.Route

@Dao
interface RouteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(route: Route): Long

    @Query("select * from Trasy where FKturysta = :FKturysta")
    fun getAll(FKturysta: Int): LiveData<List<Route>>

    @Query("delete from Trasy")
    fun deleteAll()

}