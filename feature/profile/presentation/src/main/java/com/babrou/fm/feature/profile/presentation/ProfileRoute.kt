package com.babrou.fm.feature.profile.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.babrou.fm.core.navigation.INavigationManager

@Composable
fun ProfileScreen(
    navigationManager: INavigationManager,
) {
    ProfileScreenInternal(
        navigationManager = navigationManager
    )
}

@Composable
internal fun ProfileScreenInternal(
    navigationManager: INavigationManager,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile Screen")
    }
}