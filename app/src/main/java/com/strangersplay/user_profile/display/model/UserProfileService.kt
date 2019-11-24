package com.strangersplay.user_profile.display.model

class UserProfileService(private val userProfileDataProvider: UserProfileDataProvider){

    suspend fun getUserProfileInformation(id: Int): UserData {
        return userProfileDataProvider.fetchUserProfileData(id)
    }

}