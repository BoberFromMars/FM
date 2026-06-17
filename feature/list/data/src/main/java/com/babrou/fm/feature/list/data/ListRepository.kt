package com.babrou.fm.feature.list.data

import com.babrou.fm.core.api.Result
import com.babrou.fm.core.api.map
import com.babrou.fm.core.base.BaseRepository
import com.babrou.fm.core.local.IPreferencesManager
import com.babrou.fm.feature.list.domain.IListRepository
import com.babrou.fm.feature.list.data.remote.ListService
import com.babrou.fm.feature.list.domain.model.BalanceChangeModel
import com.babrou.fm.feature.list.domain.model.BalanceTypeModel
import javax.inject.Inject

internal class ListRepository @Inject constructor (
    private val listService: ListService,
    private val prefs: IPreferencesManager
) : BaseRepository(), IListRepository {

    override suspend fun getBalanceChanges(accountId: Int): Result<List<BalanceChangeModel>> {
        val result = safeCall(
            call = {
                listService.getBalanceChanges(accountId)
            }
        )
            .map {
               it.map { a -> BalanceChangeModel(
                    id = a.id,
                   accountId = a.accountId,
                   changeTypeId = a.changeTypeId,
                   money = a.money,
                   date = a.date,
                   comment = a.comment
               )}
            }

        return result
    }

    override suspend fun getBalanceTypes(): Result<List<BalanceTypeModel>> {
        val result = safeCall(
            call = {
                listService.getBalanceTypes()
            }
        )
            .map {
                it.map { a -> BalanceTypeModel(
                    id = a.id,
                    name = a.name,
                    isIncrement = a.isIncrement
                )}
            }
        return result
    }

    override suspend fun deleteBalanceChangeById(balanceChangeId: Int): Result<String> {
        val result = safeCall (
            call = {
                listService.deleteBalanceChanges(balanceChangeId)
            }
        )
        return result
    }
}