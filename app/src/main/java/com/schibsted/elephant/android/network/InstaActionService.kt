package com.schibsted.elephant.android.network

import com.schibsted.elephant.android.network.model.Profile
import com.schibsted.elephant.android.network.model.RegisterUserRequestBody
import okhttp3.ResponseBody
import com.schibsted.elephant.android.network.model.LeaderbordItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface InstaActionService {

    @GET("profile")
    suspend fun getProfile(): Response<Profile>

    @GET("leaderboard")
    suspend fun getLeaderbord(): Response<List<LeaderbordItem>>

    @POST("register")
    suspend fun registerUser(@Body requestBody: RegisterUserRequestBody): Response<ResponseBody>

    @DELETE("userByUuid")
    suspend fun deleteUserByUuid(@Query("uuid") uuid: String) : Response<ResponseBody>

}
