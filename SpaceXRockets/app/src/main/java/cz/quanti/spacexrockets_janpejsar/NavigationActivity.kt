package cz.quanti.spacexrockets_janpejsar

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.appbar.MaterialToolbar

abstract class NavigationActivity: AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateBeforeNavigationSetup(savedInstanceState)
        appBarConfiguration = AppBarConfiguration.Builder(getNavController().graph).build()
        setupToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            getNavController(),
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            getNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        NavigationUI.setupWithNavController(getToolbar(), getNavController(), appBarConfiguration)
        setSupportActionBar(getToolbar())
        setupActionBarWithNavController(getNavController(), appBarConfiguration)
    }

    private fun getNavController(): NavController {
        return Navigation.findNavController(getNavHostFragment())
    }

    abstract fun getToolbar(): MaterialToolbar

    abstract fun getNavHostFragment(): View

    abstract fun onCreateBeforeNavigationSetup(savedInstanceState: Bundle?)
}