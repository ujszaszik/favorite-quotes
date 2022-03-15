package com.newstore.favqs.features.quote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuoteModule {

    @Provides
    @Singleton
    fun provideQuoteService(retrofit: Retrofit): QuoteService =
        retrofit.create(QuoteService::class.java)

}