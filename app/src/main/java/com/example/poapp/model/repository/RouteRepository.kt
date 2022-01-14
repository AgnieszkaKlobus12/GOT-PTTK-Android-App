package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.RouteDAO
import com.example.poapp.model.entity.Route

class RouteRepository(private val routeDAO: RouteDAO) {

    fun insert(route: Route): Long {
        return routeDAO.insert(route)
    }

    fun getAllForUser(idUser: Int): LiveData<List<Route>> {
        return routeDAO.getAll(idUser)
    }

    fun getRoute(id: Int): List<Route> {
        return routeDAO.getRoute(id)
    }

    fun update(route: Route) {
        routeDAO.update(route)
    }

    fun delete(routeId: Long) {
        routeDAO.delete(routeId)
    }
}