package com.babrou.fm.feature.home.domain

import com.babrou.fm.core.api.Result
import com.babrou.fm.core.models.AvailableAccountsRequestModel
import com.babrou.fm.feature.home.domain.model.AvailableAccountsModel
import retrofit2.Response
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: IHomeRepository
) {
    suspend operator fun invoke(availableAccountsRequestModel: AvailableAccountsRequestModel):
            Result<List<AvailableAccountsModel>> {
        return homeRepository.postAvailableAccounts(availableAccountsRequestModel)
    }

}