package com.bawp.quotes_test.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bawp.quotes_test.data.Quote
import com.bawp.quotes_test.data.QuotesRepository
import com.bawp.quotes_test.model.QuotesViewModel

@Composable
fun Home(
    navController: NavController,
    viewModel: QuotesViewModel = hiltViewModel(),

    ) {



    Scaffold(topBar = {
        TopAppBar() {
            Text(text = "Quotes")


        }
    }) {
      HomeContent(navController = navController, viewModel){
          Log.d("Tap", "Home: $it")
      }

    }

}

@Composable
fun HomeContent(
    navController: NavController,
    viewModel: QuotesViewModel,
    onItemClicked: (String) -> Unit) {
     viewModel.getAllQuotesNow(context = LocalContext.current)
    if (!viewModel.data.value.data.isNullOrEmpty()) {
        LazyColumn(modifier = Modifier.padding(start = 36.dp,
            top = 12.dp,
            end = 0.dp,
            bottom = 12.dp)){
            val mList = viewModel.data.value.data


            items(items = mList!!){ item: Quote ->
                QuotesCard(quote = item){
                    onItemClicked(it)
                    //TODO: navigate...
                }

            }
        }

    }else {
        Text(text = "No Quotes Found :(")
    }


}
@Composable
fun QuotesCard(
    quote: Quote,
    onItemClicked: (String) -> Unit,
              ) {

    Column(modifier = Modifier
        .wrapContentSize()
        .padding(12.dp)
        .height(190.dp)
        .clickable(onClick = {
            onItemClicked(quote.quote)
            // actions.gotoDetails(quote.quote, quote.author)

        })
        //added this for dramatic effect :)
        .clip(shape = CircleShape.copy(topEnd = CornerSize(0.dp),
            topStart = CornerSize(23.dp),
            bottomStart = CornerSize(0.dp),
            bottomEnd = CornerSize(23.dp)))
        .background(MaterialTheme.colors.primaryVariant)
        .padding(12.dp)

          ) {

        Text(
            text = """ " """,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground
            )

        Text(
            text = quote.quote,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(start = 12.dp)
            )

        Spacer(Modifier.height(12.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(12.dp),
                text = quote.author,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onBackground
                )

            Spacer(Modifier.height(8.dp))
        }
    }
}