package com.babrou.fm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.navigation.ScreenRegistry
import com.babrou.fm.ui.AppNavigation
import com.babrou.fm.core.theme.KotlinFinanceManagerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    @Inject
    lateinit var navigationManager: INavigationManager
    
    @Inject
    lateinit var screenRegistry: ScreenRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFinanceManagerTheme {
                AppNavigation(
                    navigationManager = navigationManager,
                    screenRegistry = screenRegistry
                )
            }
        }
    }
}