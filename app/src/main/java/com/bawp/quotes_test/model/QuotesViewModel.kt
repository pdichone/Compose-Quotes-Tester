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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val repository: QuotesRepository,

                                         ): ViewModel() {
     val data: MutableState<DataOrException<List<Quote>, Boolean, Exception>> = mutableStateOf(
        DataOrException(listOf(), true, Exception("")))

    init {
//        viewModelScope.launch {
//            repository.getAllQuotes(context = )
//        }

    }

     fun getAllQuotesNow(context: Context) {
         //Here we just get stuff straight from the json file!
         val dataOrException = DataOrException<List<Quote>, Boolean, Exception>()
         viewModelScope.launch {
             try {
                 dataOrException.loading = true
                 val format = Json {
                     ignoreUnknownKeys = true
                     prettyPrint = true
                     isLenient = true
                 }
                 val quoteJson = context.assets.open("quotes.json").bufferedReader().use {
                     it.readText()
                 }

                 // decode the list of string to Quote object
                 val quotesList = format.decodeFromString<List<Quote>>(quoteJson)
                 dataOrException.data = quotesList


//        viewModelScope.launch {
//             data.value.loading = true
//            Log.d("Loading", "getAllQuotesNow: Loading...")
//            data.value = repository.getQuotees(context)
//            if (!data.value.data.isNullOrEmpty()) {
//                data.value.loading = false
//                Log.d("Loading", "getAllQuotesNow: Doneee...")
//            }
//        }
             } catch (e: Exception) {
                 dataOrException.e = e
             }

         }

     }
}