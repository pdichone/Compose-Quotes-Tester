package com.bawp.quotes_test.data


class QuotesRepository() {

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