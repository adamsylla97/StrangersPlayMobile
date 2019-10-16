package com.strangersplay.register.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.strangersplay.R
import com.strangersplay.register.model.RegisterDataProvider
import com.strangersplay.register.model.RegisterService
import com.strangersplay.register.model.UserRegisterData
import com.strangersplay.register.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView {

    private val dataProvider = RegisterDataProvider()
    private val registerService = RegisterService(dataProvider)
    private val registerPresenter = RegisterPresenter(this, registerService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        actionBar?.setHomeButtonEnabled(true)

        registerDoneButton.setOnClickListener {
            registerPresenter.registerAccount()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getRegisterData(): UserRegisterData {
        return UserRegisterData(
            login = loginEditText.text.toString(),
            password = passwordEditText.text.toString(),
            repeatPassword = repeatPasswordEditText.text.toString(),
            email = emailEditText.text.toString()
        )
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun finishRegistration() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        registerPresenter.cancel()
    }
}
