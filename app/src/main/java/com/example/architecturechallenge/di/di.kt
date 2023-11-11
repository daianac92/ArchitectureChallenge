package com.example.architecturechallenge.di

import android.content.Context
import androidx.room.Room
import com.example.architecturechallenge.data.local.MoviesDao
import com.example.architecturechallenge.data.local.MoviesDataBase
import com.example.architecturechallenge.data.remote.MoviesService
import com.example.architecturechallenge.data.remote.QueryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @ApiEndpoint
    fun provideApiEndpoint(): String = "https://api.themoviedb.org/3/"

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        queryInterceptor: QueryInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(queryInterceptor)
        .build()

    @Provides
    fun provideRestAdapter(@ApiEndpoint apiEndPoint: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(apiEndPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideMoviesService(restAdapter: Retrofit): MoviesService =
        restAdapter.create(MoviesService::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MoviesDataBase {
        return Room.databaseBuilder(
            appContext,
            MoviesDataBase::class.java, "movies-database"
        ).build()
    }
    @Provides
    fun provideDao(appDatabase: MoviesDataBase): MoviesDao {
        return appDatabase.moviesDao()
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndpoint