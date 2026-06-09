package com.babrou.fm.feature.home.presentation

import androidx.compose.runtime.Composable
import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.navigation.IScreenProvider
import com.babrou.fm.feature.home.navigation.HomeRoute
import javax.inject.Inject

/**
 * Screen provider for Home feature.
 * Provides screens for HomeRoute.
 */
class HomeScreenProvider @Inject constructor() : IScreenProvider {
    @Composable
    override fun provideScreen(
        route: INavigationItem,
        navigationManager: INavigationManager
    ): Boolean {
        return when (route) {
            is HomeRoute -> {
                HomeScreen(navigationManager)
                true
            }
            else -> false
        }
    }
}
