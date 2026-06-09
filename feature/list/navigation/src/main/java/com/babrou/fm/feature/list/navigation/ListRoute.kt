package com.babrou.fm.feature.list.navigation

import com.babrou.fm.core.navigation.INavigationItem
import kotlinx.serialization.Serializable

@Serializable
data object ListRoute : INavigationItem {
    override val route: String = "route_list"
}
