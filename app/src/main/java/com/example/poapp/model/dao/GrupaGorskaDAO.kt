package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.GrupaGorska

@Dao
interface GrupaGorskaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(grupaGorska: GrupaGorska)

    @Query("select * from GrupyGorskie")
    fun getAllGrupy(): LiveData<List<GrupaGorska>>

    @Query("select * from grupygorskie where nazwa = :nazwaG")
    fun getGrupaGorska(nazwaG: String): LiveData<List<GrupaGorska>>

    @Query("select * from grupygorskie where id = :idG")
    fun getGrupaGorska(idG: Int): LiveData<List<GrupaGorska>>
}