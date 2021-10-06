package com.bawp.quotes_test.data

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class Quote(
    /**
     *  make sure that the fields match the json fields!!
     *  or change the add the atSerialName("match_name_from_json_field")
     */
    @SerialName("quoteText")
    val quote: String,
    @SerialName("quoteAuthor")
    val author: String )