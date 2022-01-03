package com.example.poapp.ui.turysta.trasy

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import com.example.poapp.model.entities.Trasa

class TrasyListaLiveData(private val connectivityManager: ConnectivityManager) : LiveData<Trasa>() {

    constructor(application: Application) : this(
        application.getSystemService(
            Context
                .CONNECTIVITY_SERVICE
        )
                as ConnectivityManager
    )


}