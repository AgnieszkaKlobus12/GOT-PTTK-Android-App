package com.example.poapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.poapp.ui.pracownik.spisOdc.SpisOdcActivity
import com.example.poapp.ui.przodownik.PotwierdzActivity
import com.example.poapp.ui.turysta.trasy.ZapisanieTrasyActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dowod: Button = findViewById(R.id.dowod)
        val zapisanie_trasy: Button = findViewById(R.id.zapisanie_trasy)
        val edycja_odcinka: Button = findViewById(R.id.edycja_odcinka)
        val potwierdzenie: Button = findViewById(R.id.potwierdzenie_trasy)

        zapisanie_trasy.setOnClickListener {
            val intent = Intent(this, ZapisanieTrasyActivity::class.java)
            startActivity(intent)
        }

        dowod.setOnClickListener {
            Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show()
            //TODO
        }

        edycja_odcinka.setOnClickListener {
            val intent = Intent(this, SpisOdcActivity::class.java)
            startActivity(intent)
        }

        potwierdzenie.setOnClickListener {
            val intent = Intent(this, PotwierdzActivity::class.java)
            startActivity(intent)
        }
    }

}