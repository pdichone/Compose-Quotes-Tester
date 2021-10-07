package com.bawp.quotes_test.repository

import com.bawp.quotes_test.network.QuotesApi
import javax.inject.Inject

class QuotesRepository @Inject constructor(
       private val api: QuoteApi
                                          ) {}