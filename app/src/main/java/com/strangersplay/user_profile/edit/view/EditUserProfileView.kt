package com.strangersplay.user_profile.edit.view

import com.strangersplay.user_profile.display.model.UserData
import com.strangersplay.user_profile.edit.model.UpdatedUserInformation

interface EditUserProfileView {

    fun getUpdatedUser(): UpdatedUserInformation
    fun closeFragment()

}