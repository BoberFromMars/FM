package com.babrou.fm

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.tooling.preview.Preview
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.navigation.ScreenRegistry
import com.babrou.fm.ui.AppNavigation
import com.babrou.fm.core.theme.KotlinFinanceTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    @Inject
    lateinit var navigationManager: INavigationManager
    
    @Inject
    lateinit var screenRegistry: ScreenRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFinanceTheme {
                AppNavigation(
                    navigationManager = navigationManager,
                    screenRegistry = screenRegistry
                )
            }
        }
    }
}