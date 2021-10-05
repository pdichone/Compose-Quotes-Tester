package com.bawp.quotes_test.bizcard

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.bawp.quotes_test.R

@Preview
@Composable
fun CreateBizCard() {

// State Hoisting
    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    /**
     *
     * @author Paulo Dichone
     *  First, start by showing all the different pieces in one
     *  big composable
     *  Next, refactor each part of the UI into different composables
     *  Don't forget to give students something to work on.
     *
     */
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),

        ) {
        Card(
            elevation = 4.dp, shape = RoundedCornerShape(size = 15.dp),
            //backgroundColor = MaterialTheme.colors.secondaryVariant,
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp)
            ) {
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                CreateImageProfile()
                Spacer(modifier = Modifier.height(2.dp))
                Divider()
//                Divider(
//                    modifier = Modifier.shadow(shape = CircleShape, elevation = 4.dp, clip = true),
//                    color = Color.Cyan,
//                    startIndent = 4.dp,
//                    thickness = 2.dp
//                       )
                CreateInfo()
                //Row of Buttons

                CreateButtonsRow(buttonState = buttonClickedState.value) {
                    buttonClickedState.value = !it
                    Log.d("TAG", "CreateBizCard: ${buttonClickedState.value}")
                }

                //Toggle the Content area according to the state of the button
                if (buttonClickedState.value) Content(content = "About goes there") else Box {}


            }
        }

    }
}
@Preview
@Composable
fun CreateImageProfile(modifier: Modifier = Modifier) {

    Surface(
        modifier
            .size(150.dp)
            .padding(5.dp),
        border = BorderStroke(0.5.dp, Color.LightGray),
        shape = CircleShape,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
        elevation = 4.dp
           ) {
        //image goes here
        Image(
            modifier = modifier.size(135.dp),
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = R.string.app_name.toString(),
            contentScale = ContentScale.Crop
             )
    }
//    Card(
//        modifier = modifier.padding(5.dp),
//        shape = CircleShape,
//        elevation = 3.dp,
//
//        ) {
//        Image(
//            modifier = modifier.size(135.dp),
//            painter = painterResource(id = R.drawable.profile_image),
//            contentDescription = R.string.app_name.toString(),
//            contentScale = ContentScale.Crop
//             )
//    }

}

@Composable
fun CreateInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(5.dp), horizontalAlignment = Alignment.Start
          ) {
        Text(
            text = "Miles P.",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
            )

        Text(
            text = "Android Compose Programmer", modifier = modifier.padding(top = 3.dp)
            )
        Text(
            text = "@themilesCompose",
            modifier = Modifier.padding(top = 3.dp),
            style = MaterialTheme.typography.subtitle1
            )

    }
}

@Composable
fun CreateButtonsRow(
    buttonState: Boolean, modifier: Modifier = Modifier, onButtonClicked: ((Boolean) -> Unit)?
                    ) {

    /*
      Bad practice to add a state inside of this composable
      If we want to keep the state even beyond screen configuration
      changes (i.e: screen rotation), the use rememberSaveable lambda block

       Best practice - lift the state to live in the calling composable
       i.e: state hoisting.

     */
//    val isClicked = remember {
//        mutableStateOf(false)
//    }

    Row(modifier = Modifier.padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom,
        content = {


            Button(
                shape = RectangleShape,
                onClick = {
                    onButtonClicked?.invoke(buttonState)
                },
                content = {

                    Text(
                        "Portfolio", style = MaterialTheme.typography.button
                        )


                },
                  )
        })
}

@Composable
private fun Content(content: String) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(5.dp),
        contentAlignment = Alignment.TopCenter,
       ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),

            border = BorderStroke(2.dp, color = Color.LightGray),

            ) {
            //here we add a list of portfolio item
            Portfolio(portfolios = listOf("Project 1", "Project 2", "Project 3", "Project 4"))


//            Text(
//                text = content,
//                style = MaterialTheme.typography.subtitle1,
//                modifier = Modifier.padding(6.dp)
//                )

        }
    }
}

@Composable
fun Portfolio(portfolios: List<String>) {
    LazyColumn {
        items(portfolios) { port ->
            PortfolioRow(port = port)
        }

    }

}

@Composable
fun PortfolioRow(port: String) {
    Card(modifier = Modifier
        .padding(13.dp)
        .fillMaxWidth(),
        shape = RectangleShape
        ) {
        Row(
            Modifier
                .padding(8.dp)
                // .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
                .clickable(onClick = { /* Ignoring onClick */ })
                .padding(16.dp)


           ) {
            Surface(
                Modifier.size(50.dp),
                shape = CircleShape,
                elevation = 4.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                   ) {
                //image goes here
                CreateImageProfile(modifier = Modifier.size(15.dp))
            }
            Column(
                Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
                  ) {
                Text(
                    port, fontWeight = FontWeight.Bold
                    )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = "A great project Indeed", style = MaterialTheme.typography.body2)

                }

            }
        }
    }

}

@Composable
fun SimpleList(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            ImageListItem(index = it)
        }

    }
}

@Composable
fun ImageListItem(index: Int) {
    val context = LocalContext.current
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {

        Toast.makeText(context, "$index", Toast.LENGTH_LONG).show()
    }) {

        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
                                          ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
             )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}