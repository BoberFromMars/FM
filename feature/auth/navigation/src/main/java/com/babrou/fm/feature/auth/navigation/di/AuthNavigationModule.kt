package com.babrou.fm.feature.auth.navigation.di

import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.feature.auth.navigation.LoginRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal object AuthNavigationModule {
    @Provides
    @IntoSet
    fun provideLoginRoute(): INavigationItem = LoginRoute
}
