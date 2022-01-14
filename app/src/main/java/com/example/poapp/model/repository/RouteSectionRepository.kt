package com.example.poapp.model.repository

import com.example.poapp.model.dao.RouteSectionDAO
import com.example.poapp.model.entity.RouteSection

class RouteSectionRepository(private val routeSectionDAO: RouteSectionDAO) {

    fun insert(routeSection: RouteSection): Long {
        return routeSectionDAO.insert(routeSection)
    }

    fun getRouteSectionForRoute(routeID: Long): List<RouteSection> {
        return routeSectionDAO.getRouteSectionForRoute(routeID)
    }

}