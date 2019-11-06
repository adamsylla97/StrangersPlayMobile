package com.strangersplay.user_profile.network

import com.strangersplay.user_profile.model.UserData
import retrofit2.http.GET
import retrofit2.http.Path

interface RestUserProfileService {

    @GET("/advertisement/{profileId}")
    suspend fun fetchUserProfileData(@Path("profileId") profileId: Int): UserData

}