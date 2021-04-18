package com.salampoc.ui.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.salampoc.R

class FilterActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        val toolbar: Toolbar = findViewById(R.id.f_toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.filter_nav_host) as NavHostFragment
        navController = navHostFragment.navController


        toolbar.setupWithNavController(navController)

        /**
         * (Only works when route are defined)
         *  Listener for checking which fragments is currently loaded
         */
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.filterTitleFragment) {
                toolbar.setNavigationIcon(R.drawable.ic_cross)
            }
        }

        /**
         *  setup back operation on custom toolbar back icon
         */
        toolbar.setNavigationOnClickListener {
            // navController.popBackStack()
            val id= navController.currentDestination?.id

            when(id){
                R.id.filterTitleFragment -> {
                    navController.popBackStack()
                    finish()
                }

                R.id.filterValueFragment->{
                    navController.popBackStack()
                }
            }

        }

    }


}