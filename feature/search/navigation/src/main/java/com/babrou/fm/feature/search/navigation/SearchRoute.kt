package com.babrou.fm.feature.search.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.babrou.fm.core.navigation.IBottomBarItem
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute : IBottomBarItem {
    override val route: String = "route_search"
    
    override val icon: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings"
        )
    }
}
