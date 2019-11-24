package com.strangersplay.user_profile.display.model

import com.strangersplay.user_profile.display.network.RestUserProfileService

class UserProfileDataProvider(private val restUserProfileService: RestUserProfileService) {

    suspend fun fetchUserProfileData(id: Int): UserData {
        return restUserProfileService.fetchUserProfileData(id)
    }
}
