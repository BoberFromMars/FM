package com.babrou.fm.feature.search.presentation

import androidx.compose.runtime.Composable
import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.navigation.IScreenProvider
import com.babrou.fm.feature.search.navigation.SearchRoute
import javax.inject.Inject

/**
 * Screen provider for Search feature.
 * Provides screens for SearchRoute.
 */
class SearchScreenProvider @Inject constructor() : IScreenProvider {
    @Composable
    override fun provideScreen(
        route: INavigationItem,
        navigationManager: INavigationManager
    ): Boolean {
        return when (route) {
            is SearchRoute -> {
                SearchScreen(navigationManager)
                true
            }
            else -> false
        }
    }
}
