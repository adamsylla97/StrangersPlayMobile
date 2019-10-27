package com.strangersplay.register.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.register.model.UserRegisterData
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView {

    private val registerPresenter = InstanceProvider.getRegisterPresenter(this)

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
            username = loginEditText.text.toString(),
            password = passwordEditText.text.toString(),
            active = true,
            email = emailEditText.text.toString(),
            firstName = firstNameEditText.text.toString(),
            lastName = lastNameEditText.text.toString()
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
