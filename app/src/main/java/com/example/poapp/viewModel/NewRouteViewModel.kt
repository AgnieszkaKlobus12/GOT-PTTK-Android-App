package com.example.poapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.entity.*
import com.example.poapp.model.repository.*

class NewRouteViewModel(application: Application) : AndroidViewModel(application) {

    private val routeRepository: RouteRepository
    private val mountainPassOfficialRepository: MountainPassOfficialRepository
    private val mountainPassUserRepository: MountainPassUserRepository
    private val userPointRepository: UserPointRepository
    private val officialPointRepository: OfficialPointRepository
    private val routeSectionRepository: RouteSectionRepository
    private val mountainPassProofRepository: MountainPassProofRepository
    val route =
        MutableLiveData(Route(0, 1, "", "oczekuje na wysłanie", 0))
    private var routeSections = listOf<RouteSection>()

    init {
        val database = AppDatabase.getInstance(application)
        routeRepository = RouteRepository(database.routeDAO())
        mountainPassUserRepository = MountainPassUserRepository(database.mountainPassUserDAO())
        mountainPassOfficialRepository = MountainPassOfficialRepository(database.mountainPassOfficialDAO())
        routeSectionRepository = RouteSectionRepository(database.routeSectionDAO())
        officialPointRepository = OfficialPointRepository(database.officialPointDAO())
        userPointRepository = UserPointRepository(database.userPointDAO())
        mountainPassProofRepository = MountainPassProofRepository(database.mountainPassProofDAO())
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

    fun getAllRouteSections(): List<RouteSection> {
        return routeSections
    }

    fun getAllRoutes(idUser: Int): LiveData<List<Route>> {
        return routeRepository.getAllForUser(idUser)
    }

    fun updateRoute() {
        if (route.value != null && route.value!!.id != 0) {
            routeRepository.update(route.value!!)
        }
    }

    fun removeRoute() {
        if (route.value != null && route.value!!.id != 0) {
            routeRepository.update(route.value!!)
        }
    }

    fun hasProof(routeSection: RouteSection): Boolean {
        if (mountainPassProofRepository.proofsFor(routeSection.id.toLong()).isEmpty()) {
            return false
        }
        return true
    }


    fun updateRoutePoints(): Int {
        var sum = 0
        for (section in routeSections) {
            sum += if (section.FKodcinekOficjalny != null) {
                getOfficialPass(section.FKodcinekOficjalny!!).punkty
            } else {
                getUserPass(section.FKodcinekWlasny!!).punkty.toInt()
            }
        }
        return sum
    }

    fun getStartNameForRoute(routeId: Int): String {
        val routeSection = routeSectionRepository.getRouteSectionForRoute(routeId.toLong()).first()
        return getStartPointName(routeSection)
    }

    fun getEndNameForRoute(routeId: Int): String {
        val routeSection = routeSectionRepository.getRouteSectionForRoute(routeId.toLong()).last()
        return getEndPointName(routeSection)
    }

    fun getStartPointName(routeSection: RouteSection): String {
        if (routeSection.FKodcinekOficjalny != null) {
            return getOfficialPoint(getOfficialPass(routeSection.FKodcinekOficjalny!!).FKpunktPoczatkowy).nazwa
        }
        val pass = getUserPass(routeSection.FKodcinekWlasny!!)
        if (pass.FKpunktPoczatkowyWlasny != null) {
            return getUserPoint(pass.FKpunktPoczatkowyWlasny).nazwa
        }
        return getOfficialPoint(pass.FKpunktPoczatkowyOficjalny!!).nazwa
    }

    fun getEndPointName(routeSection: RouteSection): String {
        if (routeSection.FKodcinekOficjalny != null) {
            return getOfficialPoint(getOfficialPass(routeSection.FKodcinekOficjalny!!).FKpunktKoncowy).nazwa
        }
        val pass = getUserPass(routeSection.FKodcinekWlasny!!)
        if (pass.FKpunktKoncowyWlasny != null) {
            return getUserPoint(pass.FKpunktKoncowyWlasny).nazwa
        }
        return getOfficialPoint(pass.FKpunktKoncowyOficjalny!!).nazwa
    }

    fun getThroughPointName(routeSection: RouteSection): String {
        val name: String = if (routeSection.FKodcinekOficjalny != null) {
            getOfficialPass(routeSection.FKodcinekOficjalny!!).FKpunktPosredni?.let { getOfficialPoint(it).nazwa }.toString()
        } else {
            val pass = getUserPass(routeSection.FKodcinekWlasny!!)
            if (pass.FKpunktPosredniWlasny != null) {
                getUserPoint(pass.FKpunktPosredniWlasny).nazwa
            } else {
                pass.FKpunktPosredniOficjalny?.let { getOfficialPoint(it).nazwa }.toString()
            }
        }
        return if (name != "null") {
            name
        } else {
            "-"
        }
    }

    fun getRouteSectionPoints(routeSection: RouteSection): Int {
        if (routeSection.FKodcinekOficjalny != null) {
            return getOfficialPass(routeSection.FKodcinekOficjalny!!).punkty
        }
        return getUserPass(routeSection.FKodcinekWlasny!!).punkty.toInt()
    }

    fun getRouteSectionName(routeSection: RouteSection): String {
        if (routeSection.FKodcinekOficjalny != null) {
            return getOfficialPass(routeSection.FKodcinekOficjalny!!).nazwa
        }
        return getUserPass(routeSection.FKodcinekWlasny!!).nazwa
    }

    private fun getOfficialPass(id: Int): MountainPassOfficial {
        return mountainPassOfficialRepository.geMountainPass(id)[0]
    }

    private fun getUserPass(id: Int): MountainPassUser {
        return mountainPassUserRepository.geMountainPass(id)[0]
    }

    private fun getUserPoint(id: Int): UserPoint {
        return userPointRepository.getUserPoint(id)[0]
    }

    fun getOfficialPoint(id: Int): OfficialPoint {
        return officialPointRepository.getOfficialPoint(id)[0]
    }
}