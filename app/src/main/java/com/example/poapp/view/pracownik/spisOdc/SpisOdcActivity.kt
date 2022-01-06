package com.example.poapp.view.pracownik.spisOdc

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.poapp.R
import com.example.poapp.view.pracownik.createAccount.CreateAccountFragment
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
                R.id.navigation_spis -> selectedFragment = OdcinkiListaFragment()
                R.id.navigation_account -> selectedFragment = CreateAccountFragment()
            }

            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_mountain_passes_list, selectedFragment).commit()
            }
            true
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_mountain_passes_list, OdcinkiListaFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

}