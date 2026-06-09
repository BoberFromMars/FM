package com.babrou.fm.feature.list.data.di

import com.babrou.fm.feature.list.data.ListRepository
import com.babrou.fm.feature.list.data.remote.ListService
import com.babrou.fm.feature.list.domain.IListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProviderModule {

    @Provides
    @Singleton
    fun provideListService(retrofit: Retrofit): ListService {
        return retrofit.create(ListService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideListRepository(retrofit: Retrofit): IListRepository {
//        return retrofit.create(ListRepository::class.java)
//    }
}
