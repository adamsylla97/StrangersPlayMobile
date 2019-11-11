package com.strangersplay.user_profile.display.view

import com.strangersplay.user_profile.display.model.UserData

interface UserProfileView {
    fun updateProfile(profileInformation: UserData)
    fun getUserId(): Int
}