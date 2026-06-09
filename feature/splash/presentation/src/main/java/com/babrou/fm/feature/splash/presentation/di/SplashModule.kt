package com.babrou.fm.feature.splash.presentation.di

import com.babrou.fm.core.navigation.IScreenProvider
import com.babrou.fm.feature.splash.presentation.SplashScreenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SplashModule {
    @Binds
    @IntoSet
    abstract fun bindSplashScreenProvider(
        provider: SplashScreenProvider
    ): IScreenProvider
}
