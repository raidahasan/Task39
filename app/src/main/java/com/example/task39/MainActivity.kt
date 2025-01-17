package com.example.task39

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        val navGraph: NavGraph = navController.graph
        appBarConfig = AppBarConfiguration(navGraph)
        val appBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(appBar)
        setupActionBarWithNavController(navController, appBarConfig)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener(object: NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ){
                if (destination.id == R.id.profileFragment) {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }

                if (destination.id == R.id.profileFragment || destination.id == R.id.optionsFragment) {
                    supportActionBar?.show()
                } else {
                    supportActionBar?.hide()
                }
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        val success: Boolean = navController.navigateUp(appBarConfig)
        return success || super.onSupportNavigateUp()
    }
}
