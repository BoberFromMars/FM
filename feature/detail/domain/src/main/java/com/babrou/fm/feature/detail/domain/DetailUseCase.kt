package com.babrou.fm.feature.detail.domain

import com.babrou.fm.core.api.Result
import com.babrou.fm.core.models.BalanceChangeDboModel
import com.babrou.fm.core.models.BalanceChangeResponseModel
import com.babrou.fm.feature.list.domain.IListRepository
import com.babrou.fm.feature.list.domain.model.BalanceChangeModel
import com.babrou.fm.feature.list.domain.model.BalanceTypeModel
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val detailRepository: IDetailRepository,
    private val listRepository: IListRepository
) {
    suspend fun invokeGetBalanceChangeById(id: Int): Result<BalanceChangeResponseModel> {
        return detailRepository.getBalanceChangeById(id)
    }

    suspend fun invokeGetBalanceTypes(): Result<List<BalanceTypeModel>> {
        return listRepository.getBalanceTypes()
    }

    suspend fun invokeInsertBalanceChange(model: BalanceChangeDboModel): Result<String> {
        return detailRepository.insertBalanceChange(model)
    }

    suspend fun invokeUpdateBalanceChange(model: BalanceChangeResponseModel): Result<String> {
        return detailRepository.updateBalanceChange(model)
    }

}