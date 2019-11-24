package com.strangersplay.user_profile.edit.model

import com.strangersplay.user_profile.display.model.UserData
import com.strangersplay.user_profile.edit.network.RestEditUserProfileService

class EditUserProfileDataProvider(private val restEditUserProfileService: RestEditUserProfileService) {

    suspend fun updateUserData(profileId: Int, updatedUserInformation: UpdatedUserInformation){
        return restEditUserProfileService.updateUser(profileId, updatedUserInformation)
    }

    suspend fun mockedUpdateData(userData: UserData) {

    }

}