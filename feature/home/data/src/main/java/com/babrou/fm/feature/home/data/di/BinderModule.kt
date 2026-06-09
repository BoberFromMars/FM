package com.babrou.fm.feature.home.data.di

import com.babrou.fm.core.api.ITokenRefresher
import com.babrou.fm.feature.home.data.HomeRepository
import com.babrou.fm.feature.home.domain.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BinderModule {

    @Binds

    abstract fun bindHomeRepository(
        home: HomeRepository
    ): IHomeRepository


}
