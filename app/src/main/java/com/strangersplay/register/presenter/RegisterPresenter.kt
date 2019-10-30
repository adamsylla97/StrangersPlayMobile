package com.strangersplay.register.presenter

import android.util.Log
import com.strangersplay.register.model.RegisterService
import com.strangersplay.register.view.RegisterView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.Exception
import kotlin.coroutines.CoroutineContext

class RegisterPresenter(private val registerView: RegisterView, private val registerService: RegisterService) {

    private val registerJob = Job()

    private val ioContext = registerJob + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = registerJob + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    fun registerAccount(){
        val user = registerView.getRegisterData()
        ioScope.launch {
            try {
                val response = registerService.registerAccount(user)
                if(201 == response.httpCode){
                    Log.i("supertest123","register succesfull")
                    registerView.finishRegistration()
                } else if (409 == response.httpCode){
                    mainScope.launch {
                        if("Not unique Username".equals(response.message)){
                            registerView.showToast("Login was used, try again with another one.")
                        } else if("Not unique Email".equals(response.message)){
                            registerView.showToast("Email was used, try again with another one.")
                        }
                    }
                }
            } catch (e: Exception){
                Log.i("supertest123","registration exception")
            }

        }
    }

    fun cancel(){
        registerJob.cancel()
    }

}