package com.bawp.quotes_test.data

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class QuotesRepository() {
    var list: List<Quote> by mutableStateOf(listOf())

    fun getAllQuotes(context: Context): List<Quote> {
        try {
            val format = Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            }
            val quoteJson = context.assets.open("quotes.json")
                .bufferedReader()
                .use {
                    it.readText()
                }

            // decode the list of string to Quote object
            val quotesList = format.decodeFromString<List<Quote>>(quoteJson)
            Log.d("Lista", "getAllQuotes--->: ${quotesList.toList().toString()}")
            list = quotesList
            Log.d("Quotes", "getAllQuotes--->: ${list.toString()}")


        }catch ( e: Exception) {
            Log.d("Error", "getAllQuotes: ${e.message.toString()}")}


        return list
    }

    fun getQuotes(): List<Quote> {
        return listOf(
            Quote(quote = "Genius is one percent inspiration and ninety-nine percent perspiration.",
                author = "Thomas Edison"),
            Quote(quote = "You can observe a lot just by watching.",
                author = "Yogi Berra"),
            Quote(quote = "A house divided against itself cannot stand.",
                author = "Abraham Lincoln"),
            Quote(quote = "Difficulties increase the nearer we get to the goal..",
                author = "Johann Wolfgang von Goethe"),
            Quote(quote = "Fate is in your hands and no one elses",
                author = "Byron Pulsifer")
                           )
    }
}