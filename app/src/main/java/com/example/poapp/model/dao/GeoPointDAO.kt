package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.poapp.model.entity.GeoPoint

@Dao
interface GeoPointDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(geoPoint: GeoPoint)

    @Update
    fun update(geoPoint: GeoPoint)

    @Query("select * from PunktyOficjalne")
    fun getAll(): LiveData<List<GeoPoint>>

    @Query("select * from PunktyOficjalne where id = :pointID")
    fun getGeoPoint(pointID: Int): LiveData<List<GeoPoint>>

    @Query("select * from PunktyOficjalne where nazwa = :pointName")
    fun getGeoPoint(pointName: String): LiveData<List<GeoPoint>>
}