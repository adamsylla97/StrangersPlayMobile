package com.strangersplay.user_profile.display.model

import com.strangersplay.user_profile.display.network.RestUserProfileService

class UserProfileDataProvider(private val restUserProfileService: RestUserProfileService) {

    suspend fun fetchUserProfileData(id: Int): UserData {
        return restUserProfileService.fetchUserProfileData(id)
    }

    suspend fun mockedFetchUserProfileData(id: Int): UserData {

        val comment1 = Comment(
            "Good player, I really like him",
            "Johny"
        )

        val comment2 = Comment(
            "I didnt like what he did. He shouldnt play anymore!",
            "Angry guy"
        )

        val profile = UserData(
            "Janusz",
            "Kowalski",
            "jkowalski@op.pl",
        "2.0",
            "I am really good player of chess, really really",
            "null",
            listOf(comment1, comment2)
        )

        return profile
    }

}
