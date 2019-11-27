package com.strangersplay

import android.content.Context
import android.location.LocationManager
import com.strangersplay.add_event.model.NewEventDataProvider
import com.strangersplay.add_event.model.NewEventService
import com.strangersplay.add_event.network.RestNewEventService
import com.strangersplay.add_event.presenter.NewEventPresenter
import com.strangersplay.add_event.view.NewEventView
import com.strangersplay.categories.model.CategoryDataProvider
import com.strangersplay.categories.model.CategoryService
import com.strangersplay.categories.network.RestCategoryService
import com.strangersplay.categories.presenter.CategoryPresenter
import com.strangersplay.categories.view.CategoryView
import com.strangersplay.joined_events.presenter.JoinedEventPresenter
import com.strangersplay.joined_events.view.JoinedEventView
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
import com.strangersplay.rateUser.EndedEvents.EndedEventsPresenter
import com.strangersplay.register.model.RegisterDataProvider
import com.strangersplay.register.model.RegisterService
import com.strangersplay.register.network.RestRegisterService
import com.strangersplay.register.presenter.RegisterPresenter
import com.strangersplay.register.view.RegisterView
import com.strangersplay.single_category.model.SingleCategoryDataProvider
import com.strangersplay.single_category.model.SingleCategoryService
import com.strangersplay.single_category.network.RestSingleCategoryService
import com.strangersplay.single_category.presenter.SingleCategoryPresenter
import com.strangersplay.single_category.view.SingleCategoryView
import com.strangersplay.single_event.model.SingleEventDataProvider
import com.strangersplay.single_event.model.SingleEventService
import com.strangersplay.single_event.network.RestSingleEventService
import com.strangersplay.single_event.presenter.SingleEventPresenter
import com.strangersplay.single_event.view.SingleEventView
import com.strangersplay.user_profile.display.model.UserProfileDataProvider
import com.strangersplay.user_profile.display.model.UserProfileService
import com.strangersplay.user_profile.display.network.RestUserProfileService
import com.strangersplay.user_profile.display.presenter.UserProfilePresenter
import com.strangersplay.user_profile.display.view.UserProfileView
import com.strangersplay.user_profile.edit.model.EditUserProfileDataProvider
import com.strangersplay.user_profile.edit.model.EditUserProfileService
import com.strangersplay.user_profile.edit.network.RestEditUserProfileService
import com.strangersplay.user_profile.edit.presenter.EditUserProfilePresenter
import com.strangersplay.user_profile.edit.view.EditUserProfileView

object InstanceProvider {

    fun getUserProfilePresenter(view: UserProfileView): UserProfilePresenter {
        val rest = RestServiceBuilder.build(RestUserProfileService::class.java)
        val dataProvider = UserProfileDataProvider(rest)
        val service = UserProfileService(dataProvider)
        val presenter = UserProfilePresenter(service, view)
        return presenter
    }

    fun getEditUserProfilePresenter(view: EditUserProfileView): EditUserProfilePresenter {
        val rest = RestServiceBuilder.build(RestEditUserProfileService::class.java)
        val dataProvider = EditUserProfileDataProvider(rest)
        val service = EditUserProfileService(dataProvider)
        val presenter = EditUserProfilePresenter(service, view)
        return presenter
    }

    fun getRegisterPresenter(view: RegisterView): RegisterPresenter {
        val rest = RestServiceBuilder.build(RestRegisterService::class.java)
        val dataProvider = RegisterDataProvider(rest)
        val service = RegisterService(dataProvider)
        val presenter = RegisterPresenter(view, service)
        return presenter
    }

    fun getLoginPresenter(view: LoginView): LoginPresenter {
        val rest = RestServiceBuilder.build(RestLoginService::class.java)
        val dataProvider = LoginDataProvider(rest)
        val service = LoginService(dataProvider)
        val presenter = LoginPresenter(view, service)
        return presenter
    }

    fun getNewestEventPresenter(view: NewestEventView, locationManager: LocationManager): NewestEventPresenter {
        val rest = RestServiceBuilder.build(RestNewestEventService::class.java)
        val dataProvider = NewestEventDataProvider(rest)
        val service = NewestEventService(dataProvider)
        val presenter = NewestEventPresenter(service, view, locationManager)
        return presenter
    }

    fun getSingleCategoryPresenter(view: SingleCategoryView): SingleCategoryPresenter {
        val rest = RestServiceBuilder.build(RestSingleCategoryService::class.java)
        val dataProvider = SingleCategoryDataProvider(rest)
        val service = SingleCategoryService(dataProvider)
        val presenter = SingleCategoryPresenter(service, view)
        return presenter
    }

    fun getSingleEventPresenter(view: SingleEventView): SingleEventPresenter {
        val rest = RestServiceBuilder.build(RestSingleEventService::class.java)
        val dataProvider = SingleEventDataProvider(rest)
        val service = SingleEventService(dataProvider)
        val presenter = SingleEventPresenter(service, view)

        return presenter

    }

    fun getCategoryPresenter(view: CategoryView): CategoryPresenter {
        val rest = RestServiceBuilder.build(RestCategoryService::class.java)
        val dataProvider = CategoryDataProvider(rest)
        val service = CategoryService(dataProvider)
        val presenter = CategoryPresenter(service, view)
        return presenter
    }

    fun getCategoriesService():CategoryService{
        val rest = RestServiceBuilder.build(RestCategoryService::class.java)
        val dataProvider = CategoryDataProvider(rest)
        val service = CategoryService(dataProvider)
        return service
    }

    fun getNewEventPresenter(view: NewEventView): NewEventPresenter {
        val rest = RestServiceBuilder.build(RestNewEventService::class.java)
        val dataProvider=NewEventDataProvider(rest)
        val service = NewEventService(dataProvider)
        val presenter = NewEventPresenter(view,service)
        return presenter
          }

    fun getEndedEventPresenter(view: NewestEventView,locationManager: LocationManager): EndedEventsPresenter {
        val rest = RestServiceBuilder.build(RestNewestEventService::class.java)
        val dataProvider = NewestEventDataProvider(rest)
        val service = NewestEventService(dataProvider)
        val presenter = EndedEventsPresenter(service, view,locationManager)
        return presenter
    }

    }

    fun getJoinedEventPresenter(view: JoinedEventView, locationManager: LocationManager): JoinedEventPresenter {
        val rest = RestServiceBuilder.build(RestNewestEventService::class.java)
        val dataProvider = NewestEventDataProvider(rest)
        val service = NewestEventService(dataProvider)
        val presenter = JoinedEventPresenter(service, view, locationManager)
        return presenter
    }
          }

    fun getEndedEventPresenter(view: NewestEventView,locationManager: LocationManager): EndedEventsPresenter {
        val rest = RestServiceBuilder.build(RestNewestEventService::class.java)
        val dataProvider = NewestEventDataProvider(rest)
        val service = NewestEventService(dataProvider)
        val presenter = EndedEventsPresenter(service, view,locationManager)
        return presenter
    }

    }

    fun getJoinedEventPresenter(view: JoinedEventView, locationManager: LocationManager): JoinedEventPresenter {
        val rest = RestServiceBuilder.build(RestNewestEventService::class.java)
        val dataProvider = NewestEventDataProvider(rest)
        val service = NewestEventService(dataProvider)
        val presenter = JoinedEventPresenter(service, view, locationManager)
        return presenter
    }
}