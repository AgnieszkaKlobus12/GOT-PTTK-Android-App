package com.example.poapp.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.dao.RouteDAO
import com.example.poapp.model.entity.Trasa
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TrasaRepository(application: Application, val user: Int) {

    private var trasaDao: RouteDAO
    private var allTrasy: LiveData<List<Trasa>>

    private val database = AppDatabase.getInstance(application)

    init {
        trasaDao = database.routeDAO()
        allTrasy = trasaDao.getAllTrasy(user)
    }

    fun insert(trasa: Trasa) {
        Single.just(trasaDao.insert(trasa)) // insert do poprawy bo nie pilnuje FKusera
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getAllTrasyForUser(idUser: Int): LiveData<List<Trasa>> {
        return allTrasy
    }
}