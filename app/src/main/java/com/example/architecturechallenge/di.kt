package com.antonioleiva.marvelcompose

import com.example.architecturechallenge.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @ApiKey
    fun provideApiKey(): String = BuildConfig.API_KEY

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiKey