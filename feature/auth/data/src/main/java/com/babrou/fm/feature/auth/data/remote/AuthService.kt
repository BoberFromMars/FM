package com.babrou.fm.feature.auth.data.remote

import com.babrou.fm.feature.auth.data.model.AuthRequestModel
import com.babrou.fm.feature.auth.data.model.AuthResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface AuthService {
    @POST("Users/auth")
    suspend fun login(@Body authRequestModel: AuthRequestModel): Response<Int>
}