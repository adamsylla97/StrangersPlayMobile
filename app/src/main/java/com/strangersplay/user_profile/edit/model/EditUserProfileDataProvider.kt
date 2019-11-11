package com.strangersplay.user_profile.edit.model

import com.strangersplay.user_profile.display.model.UserData
import com.strangersplay.user_profile.edit.network.RestEditUserProfileService

class EditUserProfileDataProvider(private val restEditUserProfileService: RestEditUserProfileService) {

    suspend fun updateUserData(userData: UserData){
        return restEditUserProfileService.updateUser()
    }

    suspend fun mockedUpdateData(userData: UserData) {

    }

}