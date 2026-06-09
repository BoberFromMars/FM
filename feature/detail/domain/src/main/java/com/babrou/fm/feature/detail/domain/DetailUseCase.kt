package com.babrou.fm.feature.detail.domain

import com.babrou.fm.core.api.Result
import com.babrou.fm.core.models.BalanceChangeResponseModel
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val detailRepository: IDetailRepository
) {
    suspend fun invokeGetBalanceChangeById(id: Int): Result<BalanceChangeResponseModel> {
        return detailRepository.getBalanceChangeById(id)
    }

}