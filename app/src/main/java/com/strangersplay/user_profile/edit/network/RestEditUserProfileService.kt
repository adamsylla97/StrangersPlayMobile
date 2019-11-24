package com.strangersplay.user_profile.edit.network

import com.strangersplay.user_profile.edit.model.UpdatedUserInformation
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestEditUserProfileService {

    @PUT("/user/{profileId}")
    suspend fun updateUser(@Path("profileId") profileId: Int, @Body updatedUserInformation: UpdatedUserInformation)

}