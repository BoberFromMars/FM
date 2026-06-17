package com.babrou.fm.feature.list.data.di

import com.babrou.fm.feature.list.data.ListRepository
import com.babrou.fm.feature.list.domain.IListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BinderModule {

    @Binds
    abstract fun bindListRepository(
        list: ListRepository
    ): IListRepository

//    @Binds
//    @Singleton
//    abstract fun bindListTypes(
//        listRepository: ListRepository
//    ): IListRepository

}
