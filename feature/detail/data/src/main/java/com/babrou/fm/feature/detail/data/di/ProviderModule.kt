package com.babrou.fm.feature.detail.data.di

import com.babrou.fm.feature.detail.data.remote.DetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProviderModule {

    @Provides
    @Singleton
    fun provideDetailService(retrofit: Retrofit): DetailService {
        return retrofit.create(DetailService::class.java)
    }
}
