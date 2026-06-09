package com.babrou.fm.feature.home.presentation.di

import com.babrou.fm.core.base.BaseUiState
import com.babrou.fm.core.theme.component.DdItem
import com.babrou.fm.feature.home.domain.model.AvailableAccountsModel

internal data class HomeUiState (
//    val availableAccountsList: List<AvailableAccountsModel> = emptyList<AvailableAccountsModel>(),
    val selectedAccount: DdItem? = null,
    override val isLoading: Boolean = true,
    override val error: String? = null
): BaseUiState(isLoading, error)