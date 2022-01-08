package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.poapp.model.entity.Tourist
import com.example.poapp.model.entity.User

@Dao
interface TouristDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tourist: Tourist)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(tourist: Tourist)

    @Query("select * from Turysci where nrKsiazeczki = :touristId")
    fun getUser(touristId: Long): List<Tourist>

    @Delete
    fun delete(tourist: Tourist)

    @Query("delete from Turysci")
    fun deleteAll()

    @Query("select * from Turysci")
    fun getAll(): LiveData<List<Tourist>>

}