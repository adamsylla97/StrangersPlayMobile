package com.strangersplay

import com.strangersplay.login.model.LoginDataProvider
import com.strangersplay.login.model.LoginService
import com.strangersplay.login.network.RestLoginService
import com.strangersplay.login.presenter.LoginPresenter
import com.strangersplay.login.view.LoginView
import com.strangersplay.register.model.RegisterDataProvider
import com.strangersplay.register.model.RegisterService
import com.strangersplay.register.network.RestRegisterService
import com.strangersplay.register.presenter.RegisterPresenter
import com.strangersplay.register.view.RegisterView

object InstanceProvider {

    fun getRegisterPresenter(view: RegisterView): RegisterPresenter {
        val rest = RestServiceBuilder.build(RestRegisterService::class.java)
        val dataProvider = RegisterDataProvider(rest)
        val service = RegisterService(dataProvider)
        val presenter = RegisterPresenter(view,service)
        return presenter
    }

    fun getLoginPresenter(view: LoginView): LoginPresenter{
        val rest = RestServiceBuilder.build(RestLoginService::class.java)
        val dataProvider = LoginDataProvider(rest)
        val service = LoginService(dataProvider)
        val presenter = LoginPresenter(view, service)
        return presenter
    }
}