package com.koc.blogg.remote.di

import com.koc.blogg.remote.BloggService
import com.koc.blogg.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
Created by kelvin_clark on 7/15/2021 2:38 AM
 */
@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Provides
    @Singleton
    fun getApiService() : BloggService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BloggService::class.java)
}