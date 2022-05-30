package com.billythelittle.lazycolumnsexample

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.billythelittle.lazycolumnsexample.doubleheader.ExampleDoubleHeaderList
import com.billythelittle.lazycolumnsexample.indexed.ExampleIndexedDataLazyColumn
import com.billythelittle.lazycolumnsexample.indexed.ExampleIndexedLazyColumn
import com.billythelittle.lazycolumnsexample.scrollbar.ExampleLazyColumnWithScrollbar
import com.billythelittle.lazycolumnsexample.ui.theme.LazyColumnsTheme

class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    @ExperimentalCoilApi
    @ExperimentalFoundationApi
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            LazyColumnsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "Greeting") {
                        composable("Greeting") { Greeting(navController) }
                        composable("Example DoubleHeaderLazyColumn") { ExampleDoubleHeaderList(getTheData()) }
                        composable("Example IndexedLazyColumn") { ExampleIndexedLazyColumn(getTheIndexedData()) }
                        composable("Example IndexedDataLazyColumn") { ExampleIndexedDataLazyColumn(getTheIndexedData()) }
                        composable("Example LazyColumnWithScrollbar") { ExampleLazyColumnWithScrollbar((1..120).toList()) }
                    }
                }
            }
        }
    }
}


@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun Greeting(navController: NavController) {
    val features = listOf("Example DoubleHeaderLazyColumn", "Example IndexedLazyColumn",
    "Example IndexedDataLazyColumn", "Example LazyColumnWithScrollbar")
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(features) {
            Button(modifier = Modifier.padding(5.dp),
                onClick = { navController.navigate(it) }) {
                Text(text = it)
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LazyColumnsTheme {
        Greeting(rememberNavController())
    }
}