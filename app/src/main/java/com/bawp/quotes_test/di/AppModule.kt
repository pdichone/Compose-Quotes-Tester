package com.bawp.quotes_test.di

import com.bawp.quotes_test.data.QuotesRepository
import com.bawp.quotes_test.network.QuoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideQuoteRepository(
        api: QuoteApi) = QuotesRepository()



}