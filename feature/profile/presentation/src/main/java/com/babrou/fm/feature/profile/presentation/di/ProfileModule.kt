package com.babrou.fm.feature.profile.presentation.di

import com.babrou.fm.core.navigation.IScreenProvider
import com.babrou.fm.feature.profile.presentation.ProfileScreenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProfileModule {
    @Binds
    @IntoSet
    abstract fun bindProfileScreenProvider(
        provider: ProfileScreenProvider
    ): IScreenProvider
}
