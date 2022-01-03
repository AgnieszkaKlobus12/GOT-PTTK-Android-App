package com.example.poapp.ui.pracownik.spisOdc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.poapp.R
import com.example.poapp.ui.pracownik.createAccount.CreateAccountFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class SpisOdcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spis_odc)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view_prac)
        bottomNavigationView.selectedItemId = R.id.navigation_spis
        bottomNavigationView.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.navigation_spis-> selectedFragment = OdcinkiListaFragment()
                R.id.navigation_account -> selectedFragment = CreateAccountFragment()
            }

            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_spis_odc, selectedFragment).commit()
            }
            true
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_spis_odc, OdcinkiListaFragment()).commit()
    }
}