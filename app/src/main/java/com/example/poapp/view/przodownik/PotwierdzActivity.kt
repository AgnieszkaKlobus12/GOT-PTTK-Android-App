package com.example.poapp.view.przodownik

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.poapp.R
import com.example.poapp.view.turysta.egot.EGOTFragment
import com.example.poapp.view.turysta.odcinki.OdcinkiFragment
import com.example.poapp.view.turysta.trasy.TrasyListaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class PotwierdzActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_potwierdz)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view_przodownik)
        bottomNavigationView.selectedItemId = R.id.navigation_potwierdz
        bottomNavigationView.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.navigation_egot -> selectedFragment = EGOTFragment()
                R.id.navigation_trasy -> selectedFragment = TrasyListaFragment()
                R.id.navigation_odcinki -> selectedFragment = OdcinkiFragment()
                R.id.navigation_potwierdz -> selectedFragment = PotwierdzListaFragment()
            }

            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_potwierdz, selectedFragment).commit()
            }
            true
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_potwierdz, PotwierdzListaFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

}