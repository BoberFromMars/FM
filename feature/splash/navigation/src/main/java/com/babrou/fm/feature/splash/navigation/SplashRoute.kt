package com.babrou.fm.feature.splash.navigation

import com.babrou.fm.core.navigation.INavigationItem
import kotlinx.serialization.Serializable

@Serializable
data object SplashRoute : INavigationItem {
    override val route: String = "route_splash"
}
