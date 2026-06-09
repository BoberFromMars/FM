package com.babrou.fm.feature.detail.presentation.di

import com.babrou.fm.core.navigation.IScreenProvider
import com.babrou.fm.feature.detail.presentation.DetailScreenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DetailModule {
    @Binds
    @IntoSet
    abstract fun bindDetailScreenProvider(
        provider: DetailScreenProvider
    ): IScreenProvider
}
