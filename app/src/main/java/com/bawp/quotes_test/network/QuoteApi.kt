package com.bawp.quotes_test.network

import com.bawp.quotes_test.data.Quote

interface QuoteApi {
    suspend fun getAllQuotes(): List<Quote>
}