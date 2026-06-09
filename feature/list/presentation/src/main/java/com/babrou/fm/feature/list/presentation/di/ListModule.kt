package com.babrou.fm.feature.list.presentation.di

import com.babrou.fm.core.navigation.IScreenProvider
import com.babrou.fm.feature.list.presentation.ListScreenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ListModule {
    @Binds
    @Singleton
    abstract fun bindListScreenProvider(
        provider: ListScreenProvider
    ): IScreenProvider
}
