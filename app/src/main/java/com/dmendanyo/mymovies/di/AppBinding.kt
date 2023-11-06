package com.dmendanyo.mymovies.di

import com.dmendanyo.data.PermissionChecker
import com.dmendanyo.data.datasources.LocalDataSource
import com.dmendanyo.data.datasources.LocationDataSource
import com.dmendanyo.data.datasources.RemoteDataSource
import com.dmendanyo.data.repositories.LocationRepositoryImpl
import com.dmendanyo.data.repositories.MyMoviesRepositoryImpl
import com.dmendanyo.domain.repositories.LocationRepository
import com.dmendanyo.domain.repositories.MyMoviesRepository
import com.dmendanyo.mymovies.AndroidPermissionChecker
import com.dmendanyo.mymovies.database.LocalDataSourceImpl
import com.dmendanyo.mymovies.database.LocationDataSourceImpl
import com.dmendanyo.mymovies.server.RemoteDataSourceImpl
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

    @Binds
    abstract fun bindLocationRepository(repositoryImpl: LocationRepositoryImpl): LocationRepository

    @Binds
    abstract fun bindLocationDataSource(dataSource: LocationDataSourceImpl): LocationDataSource

    @Binds
    abstract fun bindPermissionChecker(permissionChecker: AndroidPermissionChecker): PermissionChecker
}
