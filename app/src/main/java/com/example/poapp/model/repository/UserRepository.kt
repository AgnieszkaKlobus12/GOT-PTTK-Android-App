package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.UzytkownikDAO
import com.example.poapp.model.entity.Uzytkownik

class UserRepository(private val userDao: UzytkownikDAO) {


    fun insert(user: Uzytkownik) {
        userDao.insert(user)
    }

    fun update(user: Uzytkownik) {
        userDao.update(user)
    }

    fun delete(user: Uzytkownik) {
        userDao.delete(user)
    }

    fun deleteAllusers() {
        userDao.deleteAllUsers()
    }

    fun getAllUsers(): LiveData<List<Uzytkownik>> {
        return userDao.getAllUsers()
    }
}