package com.babrou.fm.feature.splash.presentation

import com.babrou.fm.core.base.BaseUiState
import com.babrou.fm.core.navigation.INavigationItem

internal class SplashUiState(
    val destinationRoute: INavigationItem? = null,
    override val isLoading: Boolean = true,
    override val error: String? = null
) : BaseUiState(isLoading, error)