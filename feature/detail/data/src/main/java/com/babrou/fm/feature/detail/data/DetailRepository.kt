package com.babrou.fm.feature.detail.data

import com.babrou.fm.core.api.Result
import com.babrou.fm.core.api.map
import com.babrou.fm.core.base.BaseRepository
import com.babrou.fm.core.local.IPreferencesManager
import com.babrou.fm.core.models.BalanceChangeDboModel
import com.babrou.fm.core.models.BalanceChangeResponseModel
import com.babrou.fm.feature.detail.data.remote.DetailService
import com.babrou.fm.feature.detail.domain.IDetailRepository
import retrofit2.Response
import javax.inject.Inject

internal class DetailRepository @Inject constructor(
    private val detailService: DetailService,
    private val prefs: IPreferencesManager
) : BaseRepository(), IDetailRepository {

    override suspend fun getBalanceChangeById(id: Int): Result<BalanceChangeResponseModel> {
        val result = safeCall(
            call = {
                detailService.getBalanceChangeById(id)
            }
        )
        return result
    }

    override suspend fun insertBalanceChange(model: BalanceChangeDboModel): Result<String> {
        val result = safeCall (
            call = {
                detailService.insertBalanceChange(model)
            }
        )
        return result
    }

    override suspend fun updateBalanceChange(model: BalanceChangeResponseModel): Result<String> {
        val result = safeCall (
            call = {
                detailService.updateBalanceChange(model)
            }
        )
        return result
    }
}