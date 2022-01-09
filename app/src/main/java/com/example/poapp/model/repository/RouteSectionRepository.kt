package com.example.poapp.model.repository

import androidx.lifecycle.LiveData
import com.example.poapp.model.dao.RouteSectionDAO
import com.example.poapp.model.entity.RouteSection

class RouteSectionRepository(private val routeSectionDAO: RouteSectionDAO) {

    fun insert(routeSection: RouteSection): Long {
        return routeSectionDAO.insert(routeSection)
    }

    fun getRouteSectionForRoute(routeID: Long): LiveData<List<RouteSection>> {
        return routeSectionDAO.getRouteSectionForRoute(routeID)
    }

}