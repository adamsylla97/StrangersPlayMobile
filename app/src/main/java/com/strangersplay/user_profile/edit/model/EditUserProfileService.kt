package com.strangersplay.user_profile.edit.model

import com.strangersplay.user_profile.display.model.UserData

class EditUserProfileService(private val editUserProfileDataProvider: EditUserProfileDataProvider) {

    suspend fun updateUser(userData: UserData){
        return editUserProfileDataProvider.mockedUpdateData(userData)
    }

}