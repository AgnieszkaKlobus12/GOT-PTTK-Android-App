package com.example.poapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.entity.Route
import com.example.poapp.model.entity.RouteSection
import com.example.poapp.model.repository.RouteRepository
import com.example.poapp.model.repository.RouteSectionRepository

class NewRouteViewModel(application: Application) : AndroidViewModel(application) {

    private val routeRepository: RouteRepository
    private val routeSectionRepository: RouteSectionRepository
    val route =
        MutableLiveData(Route(0, 1, "", "", 0))
    private var routeSections = listOf<RouteSection>()


    init {
        val database = AppDatabase.getInstance(application)
        routeRepository = RouteRepository(database.routeDAO())
        routeSectionRepository = RouteSectionRepository(database.routeSectionDAO())
    }

    fun setRoute(route: Int) {
        this.route.value = routeRepository.getRoute(route)[0]
        routeSections = routeSectionRepository.getRouteSectionForRoute(route.toLong())
    }

    fun getLastSection(): RouteSection? {
        return if (routeSections.isNotEmpty()) {
            routeSections.last()
        } else {
            null
        }
    }

    fun saveRoute() {
        route.value!!.id = routeRepository.insert(route.value!!).toInt()
    }

    fun getAllRouteSections(routeId: Int = route.value!!.id): List<RouteSection> {
        return routeSections
    }

    fun getAllRoutes(idUser: Int): LiveData<List<Route>> {
        return routeRepository.getAllForUser(idUser)
    }

    fun updateRoute() {
        //TODO
    }


    fun updateRoutePoints(): Int {
        //TODO sum points for route
        return 10
    }

    fun getStartNameForRoute(routeId: Int): String {
        //TODO find first RouteSection id for this route
        return "todo"
    }

    fun getEndNameForRoute(routeId: Int): String {
        //TODO find last RouteSection id for this route
        return "todo"
    }

    fun getStartPointName(routeSection: RouteSection): String {
        //TODO get name of point - official or user
        return "todo"
    }

    fun getEndPointName(routeSection: RouteSection): String {
        //TODO get name of point - official or user
        return "todo"
    }

    fun getThroughPointName(routeSection: RouteSection): String {
        //TODO get name of point - official or user
        return "todo"
    }

    fun getRouteSectionPoints(routeSection: RouteSection): Int {
        //TODO get points - mountain pass official or user
        return 0
    }

    fun getRouteSectionName(routeSection: RouteSection): String {
        //TODO get name - mountain pass official or user
        return "todo"
    }
}