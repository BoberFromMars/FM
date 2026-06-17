package com.babrou.fm.feature.home.presentation

import android.util.Log
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
        Log.v("HOME CREATION", route.route)
        return when (route) {
            is HomeRoute -> {
                Log.v("PROVIDE HOME", "")
                HomeScreen(navigationManager)
                true
            }
            else -> false
        }
    }
}
