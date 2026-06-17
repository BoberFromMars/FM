package com.babrou.fm.feature.list.domain

import com.babrou.fm.core.api.Result
import com.babrou.fm.feature.list.domain.model.BalanceChangeModel
import com.babrou.fm.feature.list.domain.model.BalanceTypeModel

interface IListRepository {

    suspend fun getBalanceChanges(accountId: Int): Result<List<BalanceChangeModel>>

    suspend fun getBalanceTypes(): Result<List<BalanceTypeModel>>

    suspend fun deleteBalanceChangeById(balanceChangeId: Int): Result<String>
}