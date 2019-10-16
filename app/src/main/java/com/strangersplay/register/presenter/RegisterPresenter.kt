package com.strangersplay.register.presenter

import com.strangersplay.register.model.RegisterService
import com.strangersplay.register.view.RegisterView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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
            val response = registerService.registerAccount(user)
            if("success".equals(response.status)){
                registerView.finishRegistration()
            } else if ("error".equals(response.status)){
                mainScope.launch {
                    if("login_used".equals(response.message)){
                        registerView.showToast("Login was used, try again with another one.")
                    } else if("email_used".equals(response.message)){
                        registerView.showToast("Email was used, try again with another one.")
                    }
                }
            }
        }
    }

    fun cancel(){
        registerJob.cancel()
    }

}