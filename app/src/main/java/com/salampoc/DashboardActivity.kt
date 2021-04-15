package com.salampoc

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottom_nav_view: BottomNavigationView
    private lateinit var drawer_nav_view: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // step:1 find and assign navController
        val navController = findNavController(R.id.nav_host_fragment)

        // step : 2 initialize drawer
        drawerLayout = findViewById(R.id.drawer_layout)

        // step : 3 find Id of drawer
        drawer_nav_view = findViewById(R.id.drawer_nav_view)
        bottom_nav_view = findViewById(R.id.bottom_nav_view)


        // step :4
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_my_cart
            ), drawerLayout
        )

        //step:5 setupActionBarWithNavController
        setupActionBarWithNavController(navController, appBarConfiguration)

        // step:6 set up controller to both drawer navigation to not to write all fragment transactions code
        drawer_nav_view.setupWithNavController(navController)
        bottom_nav_view.setupWithNavController(navController)

        drawer_nav_view.menu.findItem(R.id.nav_share).isCheckable = false
        drawer_nav_view.menu.findItem(R.id.nav_share).setOnMenuItemClickListener {
                item ->
            when(item.itemId){
                R.id.nav_share->{
                    drawerLayout.close()
                    val intent = Intent(this,SharingActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}