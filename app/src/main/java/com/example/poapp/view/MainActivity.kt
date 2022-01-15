package com.example.poapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.poapp.databinding.ActivityMainBinding
import com.example.poapp.view.member.MountainPassesActivity
import com.example.poapp.view.leader.ConfirmRouteActivity
import com.example.poapp.view.tourist.route.SaveRouteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO
        binding.saveRoute.setOnClickListener {
            val intent = Intent(this, SaveRouteActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "TODO - bug fixes", Toast.LENGTH_SHORT).show()
        }

        //TODO
        binding.addProof.setOnClickListener {
            Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show()
        }

        binding.mountainPass.setOnClickListener {
            val intent = Intent(this, MountainPassesActivity::class.java)
            startActivity(intent)
        }

        //TODO
        binding.confirmRoute.setOnClickListener {
            Toast.makeText(this, "TODO - finish", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ConfirmRouteActivity::class.java)
            startActivity(intent)
        }
    }
}