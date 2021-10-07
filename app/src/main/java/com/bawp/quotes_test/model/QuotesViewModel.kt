package com.bawp.quotes_test.model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bawp.quotes_test.data.DataOrException
import com.bawp.quotes_test.data.Quote
import com.bawp.quotes_test.data.QuotesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
    private val repository: QuotesRepository,
    private val context: Context,
                                         ): ViewModel() {
     val data: MutableState<DataOrException<List<Quote>, Boolean, Exception>> = mutableStateOf(
        DataOrException(listOf(), true, Exception("")))

    init {
        getAllQuotesNow()
    }

    private fun getAllQuotesNow() {
        viewModelScope.launch {
             data.value.loading = true
            Log.d("Loading", "getAllQuotesNow: Loading...")
            data.value = repository.getQuotees(context)
            if (!data.value.data.isNullOrEmpty()) {
                data.value.loading = false
                Log.d("Loading", "getAllQuotesNow: Doneee...")
            }
        }
    }


}