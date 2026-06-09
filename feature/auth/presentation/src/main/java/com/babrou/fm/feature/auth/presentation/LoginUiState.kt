package com.babrou.fm.feature.auth.presentation

import com.babrou.fm.core.base.BaseUiState
import com.babrou.fm.core.navigation.INavigationItem

internal data class LoginUiState(
    val destinationRoute: INavigationItem? = null,
    val shouldNavigateToSplash: Boolean = false,
    override val isLoading: Boolean = true,
    override val error: String? = null
) : BaseUiState(isLoading, error) {
    var login: String = ""
    var password: String = ""
}