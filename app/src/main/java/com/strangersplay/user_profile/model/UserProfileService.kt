package com.strangersplay.user_profile.model

class UserProfileService(private val userProfileDataProvider: UserProfileDataProvider){

    suspend fun getUserProfileInformation(id: Int): UserData {
        return userProfileDataProvider.mockedFetchUserProfileData(id)
    }

}