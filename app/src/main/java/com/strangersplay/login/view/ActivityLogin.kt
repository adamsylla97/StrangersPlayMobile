package com.strangersplay.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.login.model.LoginService
import com.strangersplay.login.model.UserLoginData
import com.strangersplay.login.presenter.LoginPresenter
import com.strangersplay.newest_event.NewestEventActivity
import com.strangersplay.register.view.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin : AppCompatActivity(), LoginView {

    val loginPresenter = InstanceProvider.getLoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
        startActivity(Intent(this, NewestEventActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenter.cancel()
    }
}
