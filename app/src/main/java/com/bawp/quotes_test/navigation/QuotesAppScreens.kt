package com.bawp.quotes_test.navigation

enum class QuotesAppScreens {
    MainScreen,
    DetailsScreen,
    FavoritesScreen;

    companion object {
        fun fromRoute(route: String?): QuotesAppScreens = when (route?.substringBefore("/")) {
            MainScreen.name -> MainScreen
            DetailsScreen.name -> DetailsScreen
            FavoritesScreen.name -> FavoritesScreen
            null -> MainScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}