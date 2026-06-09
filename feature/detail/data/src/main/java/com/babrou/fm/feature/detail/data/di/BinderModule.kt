package com.babrou.fm.feature.detail.data.di

import com.babrou.fm.core.api.ITokenRefresher
import com.babrou.fm.feature.detail.data.DetailRepository
import com.babrou.fm.feature.detail.domain.IDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BinderModule {

    @Binds
    abstract fun bindAuthRepository(
        detailRepository: DetailRepository
    ): IDetailRepository

}
