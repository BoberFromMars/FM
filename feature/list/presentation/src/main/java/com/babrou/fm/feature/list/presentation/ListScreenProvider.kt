package com.babrou.fm.feature.list.presentation

import androidx.compose.runtime.Composable
import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.navigation.IScreenProvider
import com.babrou.fm.feature.list.navigation.ListRoute
import javax.inject.Inject

/**
 * Screen provider for List feature.
 * Provides screens for ListRoute.
 */
class ListScreenProvider @Inject constructor() : IScreenProvider {
    @Composable
    override fun provideScreen(
        route: INavigationItem,
        navigationManager: INavigationManager
    ): Boolean {
        return when (route) {
            is ListRoute -> {
                ListScreen(navigationManager)
                true
            }
            else -> false
        }
    }
}
