package com.strangersplay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.strangersplay.background_service.intent_service.StrangersPlayBackgroundService
import kotlinx.android.synthetic.main.activity_main.*
import java.security.AccessControlContext

class MainActivity : AppCompatActivity(), PermissionsListener {

    private lateinit var navController: NavController
    private var permissionManager: PermissionsManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent = Intent(applicationContext, StrangersPlayBackgroundService::class.java)
        startService(intent)

        if(permissionManager == null) {
            permissionManager = PermissionsManager(this)
            permissionManager?.requestLocationPermissions(this)
        }
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navigationBar.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {

    }

    override fun onPermissionResult(granted: Boolean) {

    }


}
