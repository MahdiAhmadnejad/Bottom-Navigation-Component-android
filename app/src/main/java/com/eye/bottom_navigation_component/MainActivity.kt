package com.eye.bottom_navigation_component

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment ,
            R.id.profileFragment ,
            R.id.settingFragment))

        setupActionBarWithNavController(navController , appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

        listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment -> {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.HotPink)))
                }
                R.id.profileFragment -> {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.MediumSeaGreen)))
                }
                R.id.settingFragment -> {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.CornflowerBlue)))
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }
}