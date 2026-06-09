package com.babrou.fm.feature.profile.navigation.di

import com.babrou.fm.core.navigation.IBottomBarItem
import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.feature.profile.navigation.ProfileRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import dagger.multibindings.StringKey

@Module
@InstallIn(SingletonComponent::class)
internal object ProfileNavigationModule {
    @Provides
    @IntoSet
    fun provideProfileRoute(): INavigationItem = ProfileRoute

    @Provides
    @IntoMap
    @StringKey("3")
    fun provideProfileBottomBarItem(): IBottomBarItem = ProfileRoute
}
