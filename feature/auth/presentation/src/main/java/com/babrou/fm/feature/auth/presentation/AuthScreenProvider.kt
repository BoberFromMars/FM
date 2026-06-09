package com.babrou.fm.feature.auth.presentation

import androidx.compose.runtime.Composable
import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.navigation.IScreenProvider
import com.babrou.fm.feature.auth.navigation.LoginRoute
import javax.inject.Inject

/**
 * Screen provider for Auth feature module.
 * Provides screens for all Auth routes (LoginRoute, etc.).
 */
class AuthScreenProvider @Inject constructor() : IScreenProvider {
    @Composable
    override fun provideScreen(
        route: INavigationItem,
        navigationManager: INavigationManager
    ): Boolean {
        return when (route) {
            is LoginRoute -> {
                LoginScreen(navigationManager)
                true
            }
            else -> false
        }
    }
}
