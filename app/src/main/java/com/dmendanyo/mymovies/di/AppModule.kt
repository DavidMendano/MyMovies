package com.dmendanyo.mymovies.di

import android.app.Application
import androidx.room.Room
import com.dmendanyo.mymovies.R
import com.dmendanyo.mymovies.database.MyMoviesDao
import com.dmendanyo.mymovies.database.MyMoviesDatabase
import com.dmendanyo.mymovies.server.MyMoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val apiUrl = "https://api.themoviedb.org/3/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(okHttpClient: OkHttpClient): MyMoviesService =
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyMoviesService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): MyMoviesDatabase =
        Room.databaseBuilder(
            app,
            MyMoviesDatabase::class.java,
            "my-movies-db"
        ).build()

    @Provides
    @Singleton
    fun provideMovieDao(db: MyMoviesDatabase): MyMoviesDao =
        db.movieDao()
}
