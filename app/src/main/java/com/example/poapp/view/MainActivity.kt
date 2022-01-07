package com.example.poapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.poapp.databinding.ActivityMainBinding
import com.example.poapp.view.employee.mountainPass.MountainPassesActivity
import com.example.poapp.view.przodownik.PotwierdzActivity
import com.example.poapp.view.turysta.trasy.ZapisanieTrasyActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.zapisanieTrasy.setOnClickListener {
            val intent = Intent(this, ZapisanieTrasyActivity::class.java)
            startActivity(intent)
        }

        binding.dowod.setOnClickListener {
            Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show()
            //TODO
        }

        binding.edycjaOdcinka.setOnClickListener {
            val intent = Intent(this, MountainPassesActivity::class.java)
            startActivity(intent)
        }

        binding.potwierdzenieTrasy.setOnClickListener {
            val intent = Intent(this, PotwierdzActivity::class.java)
            startActivity(intent)
        }
    }
}