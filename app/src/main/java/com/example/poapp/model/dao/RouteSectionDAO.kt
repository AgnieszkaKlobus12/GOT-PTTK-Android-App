package com.example.poapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.RouteSection

@Dao
interface RouteSectionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(routeSection: RouteSection): Long

    @Query("delete from DowodyOdcinkow")
    fun deleteAll()