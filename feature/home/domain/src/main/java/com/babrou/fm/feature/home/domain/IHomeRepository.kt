package com.babrou.fm.feature.home.domain

import com.babrou.fm.core.api.Result
import com.babrou.fm.core.models.AvailableAccountsRequestModel
import com.babrou.fm.feature.home.domain.model.AvailableAccountsModel
import retrofit2.Response

interface IHomeRepository {
    suspend fun postAvailableAccounts(availableAccountsRequestModel: AvailableAccountsRequestModel): Result<List<AvailableAccountsModel>>
}