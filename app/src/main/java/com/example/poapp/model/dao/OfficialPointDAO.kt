package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.poapp.model.entity.OfficialPoint

@Dao
interface OfficialPointDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(geoPoint: OfficialPoint): Long

    @Update
    fun update(geoPoint: OfficialPoint)

    @Query("select * from PunktyOficjalne")
    fun getAll(): LiveData<List<OfficialPoint>>

    @Query("select * from PunktyOficjalne where id = :pointID")
    fun getOfficialPoint(pointID: Int): List<OfficialPoint>

    @Query("select * from PunktyOficjalne where nazwa = :pointName")
    fun getOfficialPoint(pointName: String): List<OfficialPoint>

    @Query("delete from OdcinkiOficjalne")
    fun deleteAll()

}