package com.example.poapp.view.turysta.trasy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.poapp.R
import com.example.poapp.view.turysta.egot.EGOTFragment
import com.example.poapp.view.turysta.odcinki.OdcinkiFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ZapisanieTrasyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zapisanie_trasy)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)
        bottomNavigationView.selectedItemId = R.id.navigation_trasy
        bottomNavigationView.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.navigation_egot -> selectedFragment = EGOTFragment()
                R.id.navigation_trasy -> selectedFragment = TrasyListaFragment()
                R.id.navigation_odcinki -> selectedFragment = OdcinkiFragment()
            }

            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_trasy, selectedFragment).commit()
            }
            true
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_trasy, TrasyListaFragment()).commit()
    }
}