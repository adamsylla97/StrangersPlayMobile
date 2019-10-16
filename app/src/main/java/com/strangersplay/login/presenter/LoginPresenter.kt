package com.strangersplay.login.presenter

import com.strangersplay.login.model.LoginService
import com.strangersplay.login.view.LoginView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginPresenter(private val loginView: LoginView, private val loginService: LoginService) {

    private val registerJob = Job()

    private val ioContext = registerJob + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = registerJob + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    fun loginToAccount(){
        val loginData = loginView.getLoginData()

        ioScope.launch {
            val response = loginService.loginToAccount(loginData.username, loginData.password)
            mainScope.launch {
                if("success".equals(response.status)) {
                    loginView.loginToAccount()
                } else if("error".equals(response.status)){
                    if("bad_login".equals(response.message)){
                        loginView.displayToast("Your username or password were incorrect, try again.")
                    } else if ("no_account".equals(response.message)){
                        loginView.displayToast("There is no such account in database.")
                    }
                }
            }
        }
    }

    fun cancel(){
        registerJob.cancel()
    }

}