package com.babrou.fm.feature.home.data.remote

import com.babrou.fm.core.models.AvailableAccountsRequestModel
import com.babrou.fm.core.models.AvailableAccountsResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeService {
    @POST("FamilyAccounts/AvailiableAccounts")
    suspend fun postAvailableAccounts(@Body availableAccountsRequestModel: AvailableAccountsRequestModel): Response<List<AvailableAccountsResponseModel>>
}