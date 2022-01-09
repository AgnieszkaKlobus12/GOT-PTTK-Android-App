package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.poapp.model.entity.UserPoint

@Dao
interface UserPointDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(geoPoint: UserPoint): Long

    @Update
    fun update(geoPoint: UserPoint)

    @Query("select * from PunktyWlasne")
    fun getAll(): LiveData<List<UserPoint>>

    @Query("select * from PunktyWlasne where id = :pointID")
    fun getOfficialPoint(pointID: Int): List<UserPoint>

    @Query("select * from PunktyWlasne where nazwa = :pointName")
    fun getOfficialPoint(pointName: String): List<UserPoint>

    @Query("delete from PunktyWlasne")
    fun deleteAll()
}