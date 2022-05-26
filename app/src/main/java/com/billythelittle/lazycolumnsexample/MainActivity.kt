package com.billythelittle.lazycolumnsexample

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import androidx.compose.ui.tooling.preview.Preview
import com.billythelittle.lazycolumnsexample.doubleheader.ExampleDoubleHeaderList
import com.billythelittle.lazycolumnsexample.indexed.ExampleIndexedDataLazyColumn
import com.billythelittle.lazycolumnsexample.indexed.ExampleIndexedLazyColumn
import com.billythelittle.lazycolumnsexample.ui.theme.LazyColumnsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class, ExperimentalFoundationApi::class)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ExampleDoubleHeaderList(getTheData())
//                    ExampleIndexedLazyColumn(getTheIndexedData())
//                    ExampleIndexedDataLazyColumn(getTheIndexedData())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LazyColumnsTheme {
        Greeting("Android")
    }
}