package com.strangersplay.user_profile.view

import com.strangersplay.user_profile.model.Comment
import com.strangersplay.user_profile.model.UserData

interface UserProfileView {
    fun updateProfile(profileInformation: UserData)
    fun getUserId(): Int
}