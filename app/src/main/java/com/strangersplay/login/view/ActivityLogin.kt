package com.strangersplay.login.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.strangersplay.InstanceProvider
import com.strangersplay.MainActivity
import com.strangersplay.R
import com.strangersplay.login.model.UserLoginData
import com.strangersplay.register.view.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

import kotlin.system.exitProcess

class ActivityLogin : AppCompatActivity(), LoginView, PermissionsListener {

    val loginPresenter = InstanceProvider.getLoginPresenter(this)

    private var permissionManager: PermissionsManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(permissionManager == null) {
            permissionManager = PermissionsManager(this)
            permissionManager?.requestLocationPermissions(this)
        }

        loginButton.setOnClickListener {
            loginPresenter.loginToAccount()
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    override fun getLoginData(): UserLoginData =
        UserLoginData(
            username = loginEditText.text.toString(),
            password = passwordEditText.text.toString()
        )

    override fun displayToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun loginToAccount() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenter.cancel()
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {

    }

    override fun onPermissionResult(granted: Boolean) {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED){
            finish()
            exitProcess(0)
        }
    }
}
