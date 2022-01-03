package com.example.poapp.model.dao

import com.example.poapp.model.entity.Uzytkownik
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UzytkownikDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(uzytkownik: Uzytkownik)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(uzytkownik: Uzytkownik)

    @Query("select * from Uzytkownicy where id = :userId")
    fun getUser(userId: Long): List<Uzytkownik>

    @Delete
    fun delete(uzytkownik: Uzytkownik)

    @Query("delete from Uzytkownicy")
    fun deleteAllUsers()

    @Query("select * from Uzytkownicy")
    fun getAllUsers(): LiveData<List<Uzytkownik>>
}