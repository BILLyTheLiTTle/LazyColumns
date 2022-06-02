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
            Button(modifier = Modifier.padding(4.dp),
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
                Text(text = "Green+Small+XL")
            }

            Button(modifier = Modifier.padding(4.dp),
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
                Text("Red+Yellow+XL+Small")
            }

            Button(modifier = Modifier.padding(4.dp),
                contentPadding = PaddingValues(4.dp),
                onClick = {
                    scrollbarSettings.value = LazyColumnScrollbarSettings()
                }
            ) {
                Text("Default")
            }
        }

    }
}