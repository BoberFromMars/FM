package com.babrou.fm.core.navigation

import androidx.compose.runtime.Composable

interface IBottomBarItem : INavigationItem {
    val icon: @Composable () -> Unit
}