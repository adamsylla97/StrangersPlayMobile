package com.strangersplay.login.presenter

import android.util.Log
import com.strangersplay.login.model.LoginService
import com.strangersplay.login.view.LoginView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.Exception

class LoginPresenter(private val loginView: LoginView, private val loginService: LoginService) {

    private val registerJob = Job()

    private val ioContext = registerJob + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = registerJob + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    fun loginToAccount(){
        val loginData = loginView.getLoginData()

        ioScope.launch {
            try{
                val response = loginService.loginToAccount(loginData)

                Log.i("supertest123",response.userId.toString())

                mainScope.launch {
                    if(200 == response.httpCode) {
                        Log.i("supertest123","$response  $loginData")
                        loginView.loginToAccount()
                    } else if(401 == response.httpCode){
                        loginView.displayToast("Podano nieprawidlowe login lub haslo.")
                    }
                }
            }catch (e: Exception){
                Log.i("supertest123","Podano nieprawidlowe login lub haslo.    ${loginData}   ${e.message}")
            }
        }
    }

    fun cancel(){
        registerJob.cancel()
    }

}