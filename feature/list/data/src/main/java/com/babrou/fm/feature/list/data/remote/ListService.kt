package com.babrou.fm.feature.list.data.remote

import com.babrou.fm.core.models.BalanceChangeResponseModel
import com.babrou.fm.core.models.BalanceTypesResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ListService {
    @GET("BalanceChanges/account/{id}")
    suspend fun getBalanceChanges(@Path("id") accountId: Int): Response<List<BalanceChangeResponseModel>>
    @GET("BalanceChangeTypes")
    suspend fun getBalanceTypes(): Response<List<BalanceTypesResponseModel>>
    @DELETE("BalanceChanges/{id}")
    suspend fun deleteBalanceChanges(@Path("id") balanceChangeId: Int): Response<String>
}