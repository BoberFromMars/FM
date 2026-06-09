package com.babrou.fm.feature.splash.navigation.di

import com.babrou.fm.core.navigation.INavigationItem
import com.babrou.fm.feature.splash.navigation.SplashRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SplashNavigationModule {
    @Provides
    @IntoSet
    fun provideSplashRoute(): INavigationItem = SplashRoute

    @Provides
    @Singleton
    fun provideStartDestination(): INavigationItem {
        return SplashRoute
    }
}
