package com.babrou.fm.di

import com.babrou.fm.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return "http://10.0.2.2:5108/api/"
//        return BuildConfig.BASE_URL
    }
}
