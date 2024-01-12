/*
 * LazyColumns: Jetpack Compose enhanced LazyColumns and usage examples
 *   Copyright (C) 2022  Vasilis Tsapalos
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *
 *   the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.billythelittle.lazycolumnsexample

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
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
import com.billythelittle.lazycolumnsexample.netflixscreen.ExampleNetflixLazyScreen
import com.billythelittle.lazycolumnsexample.scrollbar.ExampleLazyColumnWithScrollbar
import com.billythelittle.lazycolumnsexample.ui.theme.LazyColumnsTheme
import kotlinx.collections.immutable.toPersistentList



val features = listOf("Example DoubleHeaderLazyColumn", "Example IndexedLazyColumn",
    "Example IndexedDataLazyColumn", "Example LazyColumnWithScrollbar", "Example NetflixLazyScreen")

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            LazyColumnsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "Greeting") {
                        composable("Greeting") { Greeting(navController) }
                        composable(features[0]) { ExampleDoubleHeaderList(getTheData()) }
                        composable(features[1]) { ExampleIndexedLazyColumn(getTheIndexedData()) }
                        composable(features[2]) { ExampleIndexedDataLazyColumn(getTheIndexedData()) }
                        composable(features[3]) { ExampleLazyColumnWithScrollbar((1..120).toPersistentList()) }
                        composable(features[4]) { ExampleNetflixLazyScreen() }
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