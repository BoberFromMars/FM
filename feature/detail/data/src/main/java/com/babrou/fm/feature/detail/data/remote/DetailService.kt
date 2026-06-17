package com.babrou.fm.feature.detail.data.remote

import com.babrou.fm.core.api.Result
import com.babrou.fm.core.models.BalanceChangeDboModel
import com.babrou.fm.core.models.BalanceChangeResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DetailService {
    @GET("BalanceChanges/{id}")
    suspend fun getBalanceChangeById(@Path("id") id: Int): Response<BalanceChangeResponseModel>

    @POST("BalanceChanges")
    suspend fun insertBalanceChange(@Body model: BalanceChangeDboModel): Response<String>

    @PUT("BalanceChanges")
    suspend fun updateBalanceChange(@Body balanceChange: BalanceChangeResponseModel) : Response<String>
}