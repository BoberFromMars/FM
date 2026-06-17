package com.babrou.fm.feature.list.presentation.di

import com.babrou.fm.core.base.BaseUiState
import com.babrou.fm.feature.list.presentation.models.BalanceChangeTableViewModel
import com.babrou.fm.feature.list.presentation.models.BalanceChangeUiModel

internal data class ListUiState(
    val items: List<BalanceChangeUiModel> = listOf(),
    val displayedItems: List<BalanceChangeTableViewModel> = listOf(),
    val hasData: Boolean = false,
    override val isLoading: Boolean = true,
    override val error: String? = null
): BaseUiState(isLoading, error)