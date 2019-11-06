package com.strangersplay.user_profile.presenter

import com.strangersplay.user_profile.model.UserProfileService
import com.strangersplay.user_profile.view.UserProfileView
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

    fun displayUserInformation() {

        ioScope.launch {
            val userId = userProfileView.getUserId()
            val profileInformation = userProfileService.getUserProfileInformation(userId)
            mainScope.launch {
                userProfileView.updateProfile(profileInformation)
            }
        }

    }

}