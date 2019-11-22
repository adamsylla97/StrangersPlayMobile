package com.strangersplay.user_profile.display.presenter

import com.strangersplay.Config
import com.strangersplay.user_profile.display.model.UserData
import com.strangersplay.user_profile.display.model.UserProfileService
import com.strangersplay.user_profile.display.view.UserProfileView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserProfilePresenter(
    private val userProfileService: UserProfileService,
    private val userProfileView: UserProfileView
){

    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    private var profile: UserData? = null

    fun displayUserInformation() {

        ioScope.launch {
            val userId = userProfileView.getUserId()
            val profileInformation = userProfileService.getUserProfileInformation(userId)
            profile = profileInformation
            mainScope.launch {
                userProfileView.updateProfile(profileInformation)
            }
        }

    }

    fun getProfile(): UserData? {
        return profile
    }

}