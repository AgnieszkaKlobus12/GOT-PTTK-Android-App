package com.example.poapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.poapp.R
import com.example.poapp.view.pracownik.spisOdc.SpisOdcActivity
import com.example.poapp.view.turysta.trasy.ZapisanieTrasyActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dowód: Button = findViewById(R.id.dowód)
        val zapisanie_trasy: Button = findViewById(R.id.zapisanie_trasy)
        val edycja_odcinka: Button = findViewById(R.id.edycja_odcinka)
        val potwierdzenie: Button = findViewById(R.id.potwierdzenie_trasy)

        zapisanie_trasy.setOnClickListener {
            val intent = Intent(this, ZapisanieTrasyActivity::class.java)
            startActivity(intent)
        }

        dowód.setOnClickListener {
            TODO()
        }

        edycja_odcinka.setOnClickListener {
            val intent = Intent(this, SpisOdcActivity::class.java)
            startActivity(intent)
        }

        potwierdzenie.setOnClickListener {
            TODO()
        }
    }
}