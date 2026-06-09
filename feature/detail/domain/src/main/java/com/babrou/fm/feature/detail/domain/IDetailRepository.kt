package com.babrou.fm.feature.detail.domain

import com.babrou.fm.core.api.Result
import com.babrou.fm.core.models.BalanceChangeResponseModel

interface IDetailRepository {
    suspend fun getBalanceChangeById(id: Int): Result<BalanceChangeResponseModel>
}
