package com.babrou.fm.feature.profile.presentation

import androidx.compose.runtime.Composable
import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.navigation.IScreenProvider
import com.babrou.fm.feature.profile.navigation.ProfileRoute
import javax.inject.Inject

/**
 * Screen provider for Profile feature.
 * Provides screens for ProfileRoute.
 */
class ProfileScreenProvider @Inject constructor() : IScreenProvider {
    @Composable
    override fun provideScreen(
        route: INavigationItem,
        navigationManager: INavigationManager
    ): Boolean {
        return when (route) {
            is ProfileRoute -> {
                ProfileScreen(navigationManager)
                true
            }
            else -> false
        }
    }
}
