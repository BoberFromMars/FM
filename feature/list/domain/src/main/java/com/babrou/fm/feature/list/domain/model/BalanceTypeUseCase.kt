package com.babrou.fm.feature.list.domain.model

import com.babrou.fm.core.api.Result
import com.babrou.fm.feature.list.domain.IListRepository
import javax.inject.Inject

class BalanceTypeUseCase @Inject constructor(
    private val listRepository: IListRepository
//    private val
) {

    suspend operator fun invoke(): Result<List<BalanceTypeModel>> {
        return listRepository.getBalanceTypes()
    }
}