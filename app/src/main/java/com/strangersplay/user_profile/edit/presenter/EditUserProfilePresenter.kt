package com.strangersplay.user_profile.edit.presenter

import com.strangersplay.Config
import com.strangersplay.user_profile.edit.model.EditUserProfileService
import com.strangersplay.user_profile.edit.view.EditUserProfileView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EditUserProfilePresenter(private val editUserProfileService: EditUserProfileService,
                               private val editUserProfileView: EditUserProfileView){

    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    fun updateUser(){

        ioScope.launch {

            val updatedUserInformation = editUserProfileView.getUpdatedUser()
            editUserProfileService.updateUser(Config.userToken, updatedUserInformation)

        }

    }

}