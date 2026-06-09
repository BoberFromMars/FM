package com.babrou.fm.feature.search.navigation.di

import com.babrou.fm.core.navigation.IBottomBarItem
import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.feature.search.navigation.SearchRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import dagger.multibindings.StringKey

@Module
@InstallIn(SingletonComponent::class)
internal object SearchNavigationModule {
    @Provides
    @IntoSet
    fun provideSearchRoute(): INavigationItem = SearchRoute

    @Provides
    @IntoMap
    @StringKey("2")
    fun provideSearchBottomBarItem(): IBottomBarItem = SearchRoute
}
