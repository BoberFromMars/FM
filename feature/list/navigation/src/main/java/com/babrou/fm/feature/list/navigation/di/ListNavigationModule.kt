package com.babrou.fm.feature.list.navigation.di

import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.feature.list.navigation.ListRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal object ListNavigationModule {
    @Provides
    @IntoSet
    fun provideListRoute(): INavigationItem = ListRoute
}
