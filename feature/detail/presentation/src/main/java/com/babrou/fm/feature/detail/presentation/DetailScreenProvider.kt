package com.babrou.fm.feature.detail.presentation

import androidx.compose.runtime.Composable
import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.navigation.IScreenProvider
import com.babrou.fm.feature.detail.navigation.DetailRoute
import javax.inject.Inject

/**
 * Screen provider for Detail feature.
 * Provides screens for DetailRoute.
 */
class DetailScreenProvider @Inject constructor() : IScreenProvider {
    @Composable
    override fun provideScreen(
        route: INavigationItem,
        navigationManager: INavigationManager
    ): Boolean {
        return when (route) {
            is DetailRoute -> {
                DetailScreen(navigationManager, id = route.id, actionIntent =  route.intent)
                true
            }
            else -> false
        }
    }
}
