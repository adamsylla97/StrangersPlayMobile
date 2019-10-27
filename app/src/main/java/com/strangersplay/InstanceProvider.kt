package com.strangersplay

import com.strangersplay.login.model.LoginDataProvider
import com.strangersplay.login.model.LoginService
import com.strangersplay.login.network.RestLoginService
import com.strangersplay.login.presenter.LoginPresenter
import com.strangersplay.login.view.LoginView
import com.strangersplay.newest_event.model.NewestEventDataProvider
import com.strangersplay.newest_event.model.NewestEventService
import com.strangersplay.newest_event.network.RestNewestEventService
import com.strangersplay.newest_event.presenter.NewestEventPresenter
import com.strangersplay.newest_event.view.NewestEventView
import com.strangersplay.register.model.RegisterDataProvider
import com.strangersplay.register.model.RegisterService
import com.strangersplay.register.network.RestRegisterService
import com.strangersplay.register.presenter.RegisterPresenter
import com.strangersplay.register.view.RegisterView
import com.strangersplay.single_event.model.SingleEventDataProvider
import com.strangersplay.single_event.model.SingleEventService
import com.strangersplay.single_event.network.RestSingleEventService
import com.strangersplay.single_event.presenter.SingleEventPresenter
import com.strangersplay.single_event.view.SingleEventView

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

    fun getNewestEventPresenter(view: NewestEventView): NewestEventPresenter{
        val rest = RestServiceBuilder.build(RestNewestEventService::class.java)
        val dataProvider = NewestEventDataProvider(rest)
        val service = NewestEventService(dataProvider)
        val presenter = NewestEventPresenter(service, view)
        return presenter
    }

    fun getSingleEventPresenter(view: SingleEventView): SingleEventPresenter {
        val rest = RestServiceBuilder.build(RestSingleEventService::class.java)
        val dataProvider = SingleEventDataProvider(rest)
        val service = SingleEventService(dataProvider)
        val presenter = SingleEventPresenter(service, view)

        return presenter

    }
}