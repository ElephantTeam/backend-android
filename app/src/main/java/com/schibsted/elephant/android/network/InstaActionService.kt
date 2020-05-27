package com.schibsted.elephant.android.network

import com.schibsted.elephant.android.com.schibsted.elephant.android.model.Profile
import com.schibsted.elephant.android.model.LeaderbordItem
import retrofit2.Response
import retrofit2.http.GET

interface InstaActionService {

    @GET("profile")
    suspend fun getProfile(): Response<Profile>

    @GET("leaderbord")
    suspend fun getLeaderbord(): Response<List<LeaderbordItem>>

}