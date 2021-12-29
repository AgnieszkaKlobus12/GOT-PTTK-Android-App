package com.example.poapp.model.repository

import android.app.Application
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.dao.UzytkownikDAO
import com.example.poapp.model.entities.Uzytkownik
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(application: Application) {

    private var userDao: UzytkownikDAO
    private var allUsers: List<Uzytkownik>

    private val database = AppDatabase.getInstance(application)

    init {
        userDao = database.uzytkownikDao()
        allUsers = userDao.getAllUsers()
    }

    fun insert(user: Uzytkownik) {
        Single.just(userDao.insert(user))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun update(user: Uzytkownik) {
        Single.just(userDao.update(user))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun delete(user: Uzytkownik) {
        Single.just(userDao.delete(user))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun deleteAllusers() {
        Single.just(userDao.deleteAllUsers())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getAllUsers(): List<Uzytkownik> {
        return allUsers
    }
}