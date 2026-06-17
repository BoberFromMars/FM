package com.babrou.fm.feature.list.domain

import com.babrou.fm.core.api.Result
import com.babrou.fm.feature.list.domain.model.BalanceChangeModel
import javax.inject.Inject

class BalanceChangeUseCase @Inject constructor(
    private val listRepository: IListRepository
//    private val
) {

    suspend fun invokeGetBalance(accountId: Int): Result<List<BalanceChangeModel>> {
        return listRepository.getBalanceChanges(accountId)
    }

    suspend fun invokeDeleteBalance(id: Int) : Result<String> {
        return listRepository.deleteBalanceChangeById(id)
    }

}