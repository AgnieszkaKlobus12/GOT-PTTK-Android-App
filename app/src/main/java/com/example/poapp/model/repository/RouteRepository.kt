package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.RouteDAO
import com.example.poapp.model.entity.Route

class RouteRepository(private val routeDAO: RouteDAO) {

    fun insert(route: Route) {
        return routeDAO.insert(route)
    }

    fun getAllForUser(idUser: Int): LiveData<List<Route>> {
        return routeDAO.getAll(idUser)
    }
}