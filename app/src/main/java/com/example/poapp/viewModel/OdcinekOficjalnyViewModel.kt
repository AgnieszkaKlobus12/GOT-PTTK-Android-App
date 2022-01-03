package com.example.poapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.entity.GrupaGorska
import com.example.poapp.model.entity.OdcinekOficjalny
import com.example.poapp.model.entity.PasmoGorskie
import com.example.poapp.model.entity.PunktOficjalny
import com.example.poapp.model.repository.GrupaGroskaRepository
import com.example.poapp.model.repository.OdcinekOficjalnyRepository
import com.example.poapp.model.repository.PasmoGorskieRepository
import com.example.poapp.model.repository.PunktOficjalnyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OdcinekOficjalnyViewModel(application: Application) : AndroidViewModel(application) {

    private val odcinekRepository: OdcinekOficjalnyRepository
    private val punktRepository: PunktOficjalnyRepository
    private val grupaGroskaRepository: GrupaGroskaRepository
    private val pasmoGorskieRepository: PasmoGorskieRepository

    init {
        val database = AppDatabase.getInstance(application)
        odcinekRepository = OdcinekOficjalnyRepository(database.odcinekOficjlanyDao())
        punktRepository = PunktOficjalnyRepository(database.punktOficjalnyDao())
        grupaGroskaRepository = GrupaGroskaRepository(database.grupaGorskaDao())
        pasmoGorskieRepository= PasmoGorskieRepository(database.pasmoGorskieDao())
    }

    fun addOdcinekOficjalny(odcinek: OdcinekOficjalny) {
        viewModelScope.launch(Dispatchers.IO) {
            odcinekRepository.insert(odcinek)
        }
    }

    fun getAll(): LiveData<List<OdcinekOficjalny>> {
        return odcinekRepository.getAllOdcinki()
    }

    fun getOdcinekOficjalny(id: Int): LiveData<List<OdcinekOficjalny>> {
        return odcinekRepository.getOdcinek(id)
    }

    fun addPunktOficjalny(punkt: PunktOficjalny){
        viewModelScope.launch(Dispatchers.IO) {
            punktRepository.insert(punkt)
        }
    }

    fun getPunktOficjalny(id: Int): LiveData<List<PunktOficjalny>> {
        return punktRepository.getPunkt(id)
    }

    fun getPunktOficjalny(nazwa: String): LiveData<List<PunktOficjalny>> {
        return punktRepository.getPunkt(nazwa)
    }

    fun getGrupa(id: Int): LiveData<List<GrupaGorska>> {
        return grupaGroskaRepository.getGrupa(id)
    }

    fun getPasmo(id: Int): LiveData<List<PasmoGorskie>> {
        return pasmoGorskieRepository.getPasmo(id)
    }

}