package com.babrou.fm.feature.detail.data.remote

import com.babrou.fm.core.models.BalanceChangeResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {
    @GET("BalanceChanges/account/{id}")
    suspend fun getBalanceChangeById(@Path("id") accountId: Int): Response<BalanceChangeResponseModel>
}