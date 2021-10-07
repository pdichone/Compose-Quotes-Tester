package com.bawp.quotes_test.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bawp.quotes_test.QuotesApp
import com.bawp.quotes_test.data.QuotesRepository
import com.bawp.quotes_test.model.QuotesViewModel
import com.bawp.quotes_test.screens.home.Home


@Composable
fun QuoteNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = QuotesAppScreens.MainScreen.name){
        composable(QuotesAppScreens.MainScreen.name) {
            val homeViewModel = hiltViewModel<QuotesViewModel>()
            Home(navController = navController, viewModel = homeViewModel)
        }
    }
}