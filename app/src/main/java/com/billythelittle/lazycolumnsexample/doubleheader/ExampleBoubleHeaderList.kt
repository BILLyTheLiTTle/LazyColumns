package com.billythelittle.lazycolumnsexample.doubleheader

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.billythelittle.lazycolumns.DoubleHeaderLazyColumn
import com.billythelittle.lazycolumnsexample.CustomListItem

@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ExampleDoubleHeaderList(data: List<CustomListItem>) {
    DoubleHeaderLazyColumn(data = data,
        modifier = Modifier.height(700.dp),
        headerContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = 5.dp,
                backgroundColor = Color.Red
            ) {
                Text(text = it, fontSize = 20.sp, modifier = Modifier.padding(5.dp))
            }
        },
        subHeaderContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = 5.dp,
                backgroundColor = Color.Cyan
            ) {
                Text(text = it, fontSize = 17.sp, modifier = Modifier.padding(5.dp))
            }
        },
        itemContent = {
            val item = it as CustomListItem
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable { },
                elevation = 10.dp
            ) {
                Row() {
                    Image(
                        painter = rememberImagePainter(
                            data = item.imageUrl,
                            builder = {
                                transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(5.dp)
                    )
                    Column() {
                        Text(text = item.title, fontSize = 15.sp)
                        Text(text = item.description, fontSize = 10.sp)
                    }
                }
            }
        }
    )
}