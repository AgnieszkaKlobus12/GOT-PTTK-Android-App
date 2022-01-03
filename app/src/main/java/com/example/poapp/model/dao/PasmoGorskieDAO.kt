package com.example.poapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poapp.model.entity.PasmoGorskie

@Dao
interface PasmoGorskieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pasmoGorskie: PasmoGorskie)

    @Query("select * from PasmaGorskie")
    fun getAllPasma(): LiveData<List<PasmoGorskie>>

    @Query("select * from pasmagorskie where nazwa = :nazwaP")
    fun getPasmo(nazwaP: String): LiveData<List<PasmoGorskie>>

    @Query("select * from pasmagorskie where id = :idP")
    fun getPasmoGorskie(idP: Int): LiveData<List<PasmoGorskie>>
}