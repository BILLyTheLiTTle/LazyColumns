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
package com.billythelittle.lazycolumnsexample.scrollbar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.billythelittle.lazycolumns.LazyColumnScrollbarSettings
import com.billythelittle.lazycolumns.LazyColumnWithScrollbar

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ExampleLazyColumnWithScrollbar(data: List<Int>) {
    val scrollbarSettings = remember {
        mutableStateOf(LazyColumnScrollbarSettings())
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumnWithScrollbar(
            data = data,
            settings = scrollbarSettings.value,
            modifier = Modifier.height(500.dp)
        ) {
            items(data) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable { },
                    elevation = 10.dp
                ) {
                    Column {
                        Text(
                            text = it.toString(),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }
        }

        Row() {
            Button(modifier = Modifier.fillMaxWidth(0.5F).padding(4.dp),
                contentPadding = PaddingValues(4.dp),
                onClick = {
                    scrollbarSettings.value = scrollbarSettings.value.copy(
                        thumbColor = Color.Green,
                        trailColor = Color.Transparent,
                        thumbWidth = LazyColumnScrollbarSettings.ThumbWidth.X_LARGE,
                        thumbHeight = LazyColumnScrollbarSettings.ThumbHeight.SMALL
                    )
                }
            ) {
                Text(text = "Green + Small + Thick")
            }

            Button(modifier = Modifier.fillMaxWidth(1F).padding(4.dp),
                contentPadding = PaddingValues(4.dp),
                onClick = {
                    scrollbarSettings.value = scrollbarSettings.value.copy(
                        thumbColor = Color.Red,
                        trailColor = Color.Yellow,
                        thumbWidth = LazyColumnScrollbarSettings.ThumbWidth.SMALL,
                        thumbHeight = LazyColumnScrollbarSettings.ThumbHeight.X_LARGE
                    )
                }
            ) {
                Text("Red + Yellow + XL + Thin")
            }
        }
        Button(modifier = Modifier.padding(4.dp).fillMaxWidth(),
            contentPadding = PaddingValues(4.dp),
            onClick = {
                scrollbarSettings.value = LazyColumnScrollbarSettings()
            }
        ) {
            Text("Default")
        }
    }
}