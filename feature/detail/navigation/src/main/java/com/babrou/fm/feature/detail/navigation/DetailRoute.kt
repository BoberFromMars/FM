package com.babrou.fm.feature.detail.navigation

import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.core.util.ActionIntentEnum
import kotlinx.serialization.Serializable

@Serializable
data class DetailRoute(val id: Int, val intent: ActionIntentEnum) : INavigationItem {
    override val route: String = "route_detail/$id"
}
