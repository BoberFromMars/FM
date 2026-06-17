package com.babrou.fm.feature.list.presentation

import android.util.Log
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
        Log.v("LIST CREATION", route.route)
        return when (route) {
            is ListRoute -> {
                Log.v("PROVIDE SCREEN", "")
                ListScreen(navigationManager)
                true
            }
            else -> false
        }
    }
}
