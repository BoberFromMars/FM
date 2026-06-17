package com.babrou.fm.feature.detail.domain

import com.babrou.fm.core.api.Result
import com.babrou.fm.core.models.BalanceChangeDboModel
import com.babrou.fm.core.models.BalanceChangeResponseModel

interface IDetailRepository {
    suspend fun getBalanceChangeById(id: Int): Result<BalanceChangeResponseModel>

    suspend fun insertBalanceChange(model: BalanceChangeDboModel): Result<String>

    suspend fun updateBalanceChange(model: BalanceChangeResponseModel): Result<String>
}
