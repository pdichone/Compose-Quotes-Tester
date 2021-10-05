package com.bawp.quotes_test

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bawp.quotes_test.data.Quote
import com.bawp.quotes_test.data.QuotesRepository
import com.bawp.quotes_test.ui.theme.QuotesTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotesTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background) {
                    QuotesApp()

                }
            }
        }
    }
}


@Composable
fun QuotesApp() {
    /*
      We've now hoisted the state to the calling (this composable)
      composable.
      All other composables are now easily re-usable since they no
      longer carry the state themselves.
      Also, according to this codelab: https://developer.android.com/jetpack/compose/state
      There are three ways to declare a MutableState object in a composable:

val mutableState = remember { mutableStateOf(default) }
var value by remember { mutableStateOf(default) }
val (value, setValue) = remember { mutableStateOf(default) }
These declarations are equivalent, and are provided as syntax sugar for different uses of state. You should pick the one that produces the easiest-to-read code in the composable you're writing.

The by delegate syntax requires the following imports:
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

  Which one to use, it comes down to the person's preference, really.

     */
   // var counter by remember { mutableStateOf(0) }

    val counter = remember {
        mutableStateOf(0)
    }
    val quotes = QuotesRepository().getQuotes()

//    QuotesContent(counter, quotesList = quotes){
//        counter.value = it.plus(1)
//    }

    QuotesList(list = quotes){
        Log.d("Quote", "QuotesApp: $it")
    }
}
@Composable
fun QuotesContent(
    counter: MutableState<Int>,
    quotesList: List<Quote>,
    onButtonClicked: (Int) -> Unit) {
    /*
    Part 1:
      hoist the state to the calling composable
      Part 2: must have a single source of truth by removing the
      state to the calling composable
        **
     */
//    val counter = remember {
//        mutableStateOf(0)
//    }


    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = MaterialTheme.colors.primary)
        .clip(shape = CircleShape.copy(bottomStart = CornerSize(62.dp),
            bottomEnd = CornerSize(62.dp),
            topStart = CornerSize(0.dp),
            topEnd = CornerSize(0.dp)))) {

        Column( verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally) {

           // QuotesCard(quote = quotesList[counter.value % quotesList.size])
//            Box(modifier = Modifier.fillMaxWidth()
//                .height(220.dp)
//                .padding(6.dp)
//                .border( border = BorderStroke(1.dp,color = MaterialTheme.colors.primary),
//                    shape = RectangleShape)
//                .clip(shape = RectangleShape),
//                contentAlignment = Alignment.Center) {
//
//                Text(text = "Hello ${quotes[counter.value % quotes.size]}")
//            }

            Button(onClick = {
                onButtonClicked.invoke(counter.value)
                //decouple the state even further by removing the counter increase logic from here!
               // counter.value = counter.value + 1

            }) {
                Icon(imageVector = Icons.Default.Star, contentDescription ="Icon" )
                Text(text = "Inspire me")


            }
        }


    }



}

@Composable
fun QuotesList(list: List<Quote>, onItemClicked: (String) -> Unit ) {
    Scaffold(topBar = {
        TopAppBar() {
            Text(text = "Quotes")

        }
    }) {
        if (!list.isNullOrEmpty()) {
            LazyColumn(modifier = Modifier.padding(start = 36.dp,
                                                  top = 12.dp,
                                                  end = 0.dp,
                                                  bottom = 12.dp)){
                items(items = list){ item: Quote ->
                    QuotesCard(quote = item){
                        onItemClicked(it)
                    }

                }
            }

        }else {
            Text(text = "No Quotes Found :(")
        }

    }

}

@Composable
fun QuotesCard(quote: Quote, onItemClicked: (String) -> Unit) {

    Column(modifier = Modifier
        .wrapContentSize()
        .padding(12.dp)
        .height(190.dp)
        .clickable(onClick = {
            onItemClicked(quote.quote)
            // actions.gotoDetails(quote.quote, quote.author)

        })
        .background(MaterialTheme.colors.primaryVariant)
        .padding(12.dp)
          ) {

        Text(
            text = """ " """,
            style = typography.h4,
            color = MaterialTheme.colors.onBackground
            )

        Text(
            text = quote.quote,
            style = typography.body1,
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
                style = typography.caption,
                color = MaterialTheme.colors.onBackground
                )
            Spacer(Modifier.height(8.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuotesTestTheme {
      QuotesApp()
    }
}