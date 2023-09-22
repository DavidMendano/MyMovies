package com.dmendanyo.mymovies.di

import com.dmendanyo.data.datasources.LocalDataSource
import com.dmendanyo.data.datasources.RemoteDataSource
import com.dmendanyo.data.repositories.MyMoviesRepositoryImpl
import com.dmendanyo.domain.repositories.MyMoviesRepository
import com.dmendanyo.mymovies.datasources.LocalDataSourceImpl
import com.dmendanyo.mymovies.datasources.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBinding {

    @Binds
    abstract fun bindMyMoviesRepository(repository: MyMoviesRepositoryImpl): MyMoviesRepository

    @Binds
    abstract fun bindMyMoviesLocalDataSource(dataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindMyMoviesRemoteDataSource(dataSource: RemoteDataSourceImpl): RemoteDataSource
}
