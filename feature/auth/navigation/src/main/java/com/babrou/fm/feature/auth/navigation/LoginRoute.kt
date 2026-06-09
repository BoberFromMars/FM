package com.babrou.fm.feature.auth.navigation

import com.babrou.fm.core.navigation.INavigationItem
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute : INavigationItem {
    override val route: String = "route_login"
}
