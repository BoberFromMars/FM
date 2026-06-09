package com.babrou.fm.feature.splash.presentation

import androidx.compose.runtime.Composable
import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.navigation.IScreenProvider
import com.babrou.fm.feature.splash.navigation.SplashRoute
import javax.inject.Inject

/**
 * Screen provider for Splash feature.
 * Provides screens for SplashRoute.
 */
class SplashScreenProvider @Inject constructor() : IScreenProvider {
    @Composable
    override fun provideScreen(
        route: INavigationItem,
        navigationManager: INavigationManager
    ): Boolean {
        return when (route) {
            is SplashRoute -> {
                SplashScreen(navigationManager)
                true
            }
            else -> false
        }
    }
}
