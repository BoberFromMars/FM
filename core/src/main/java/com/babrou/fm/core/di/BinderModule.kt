package com.babrou.fm.core.di

import com.babrou.fm.core.local.IPreferencesManager
import com.babrou.fm.core.local.PreferencesManager
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.navigation.NavigationManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BinderModule {

    @Binds
    abstract fun bindPreferencesManager(
        preferencesManager: PreferencesManager
    ): IPreferencesManager

    @Binds
    abstract fun bindNavigationManager(
        navigationManager: NavigationManager
    ): INavigationManager
}