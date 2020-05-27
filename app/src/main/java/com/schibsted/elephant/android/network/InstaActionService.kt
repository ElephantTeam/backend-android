package com.schibsted.elephant.android.com.schibsted.elephant.android.network

import com.schibsted.elephant.android.network.model.Profile
import retrofit2.Response
import retrofit2.http.GET

interface InstaActionService {

    @GET("profile")
    suspend fun getProfile(): Response<Profile>

}