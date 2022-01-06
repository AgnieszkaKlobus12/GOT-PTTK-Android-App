package com.example.poapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.poapp.model.AppDatabase
import com.example.poapp.model.entity.Uzytkownik
import com.example.poapp.model.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    private var readAll: LiveData<List<Uzytkownik>>

    init {
        val database = AppDatabase.getInstance(application).userDAO()
        repository = UserRepository(database)
        readAll = repository.getAllUsers()
    }

    fun insert(user: Uzytkownik) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
    }

    fun getAll(): LiveData<List<Uzytkownik>> {
        return readAll
    }
}