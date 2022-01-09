package com.example.poapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.entity.Route
import com.example.poapp.model.repository.RouteRepository

class NewRouteViewModel(application: Application) : AndroidViewModel(application) {

    private val routeRepository: RouteRepository
    val route =
        MutableLiveData(Route(0, 0, "", "", 0))


    init {
        val database = AppDatabase.getInstance(application)
        routeRepository = RouteRepository(database.routeDAO())
    }

    fun getAllRoutes(idUser: Int): LiveData<List<Route>> {
        return routeRepository.getAllForUser(idUser)
    }

    fun getStartNameForRoute(routeId: Int): String {
        //TODO find first RouteSection id for this route
        return "todo"
    }

    fun getEndNameForRoute(routeId: Int): String {
        //TODO find last RouteSection id for this route
        return "todo"
    }

//    fun getAllRouteSections(routeId: Int = route.value!!.id): LiveData<List<RouteSection>>{
//        return
//    }

}