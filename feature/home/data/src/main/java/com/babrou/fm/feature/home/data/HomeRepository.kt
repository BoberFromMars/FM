package com.babrou.fm.feature.home.data

import android.util.Log
import com.babrou.fm.core.api.Result
import com.babrou.fm.core.api.getOrNull
import com.babrou.fm.core.api.map
import com.babrou.fm.core.base.BaseRepository
import com.babrou.fm.core.local.IPreferencesManager
import com.babrou.fm.core.models.AvailableAccountsRequestModel
import com.babrou.fm.feature.home.data.remote.HomeService
import com.babrou.fm.feature.home.domain.IHomeRepository
import com.babrou.fm.feature.home.domain.model.AvailableAccountsModel
import javax.inject.Inject

internal class HomeRepository @Inject constructor(
    private val homeService: HomeService,
    private val prefs: IPreferencesManager
) : BaseRepository(), IHomeRepository {

    override suspend fun postAvailableAccounts(availableAccountsRequestModel: AvailableAccountsRequestModel): Result<List<AvailableAccountsModel>> {
        val result = safeCall(
            call = {
                homeService.postAvailableAccounts(availableAccountsRequestModel)
            }
        )
            .map { it.map{a -> AvailableAccountsModel(id = a.id,name = a.name, balance = a.balance) }.toList() }
//
        return result
//        return TODO()
    }
}