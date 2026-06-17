package com.babrou.fm.feature.detail.presentation

import com.babrou.fm.core.base.BaseUiState

internal data class DetailUiState(
//    val id: String = "",
    val wasInitialized: Boolean = false,
    override val isLoading: Boolean = true,
    override val error: String? = null
): BaseUiState(isLoading, error)