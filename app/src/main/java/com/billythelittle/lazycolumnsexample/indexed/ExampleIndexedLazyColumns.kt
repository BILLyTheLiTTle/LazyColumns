package com.billythelittle.lazycolumnsexample.indexed

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.billythelittle.lazycolumns.IndexedDataLazyColumn
import com.billythelittle.lazycolumns.IndexedLazyColumn
import com.billythelittle.lazycolumnsexample.CustomListItem2

@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ExampleIndexedLazyColumn(data: List<CustomListItem2>,
                             indices: List<Char> = ('A'..'Z').toList()) {
    val lazyListState = rememberLazyListState()

    IndexedLazyColumn(
        // The list of the indices
        indices = indices,
        // The state of the main LazyColumn, the one with the real data
        itemsListState = lazyListState,
        // The modifier is exported for the Column, the one with the indices
        modifier = Modifier
            .background(color = Color.Transparent)
            .height(300.dp),
        // The way to connect the index with a data item (here the index item matches the first letter of the surname)
        predicate = {
            data.indexOfFirst { item ->
                item.surname.startsWith(it.toString(), true)
            }
        },
        // The list of the main data
        lazyColumnContent = {
            LazyColumn(state = lazyListState) {
                items(data) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .clickable { },
                        elevation = 10.dp
                    ) {
                        Column {
                            Text(text = it.surname,
                                fontSize = 17.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic,
                                modifier = Modifier.padding(start = 10.dp))
                            Text(text = it.name,
                                fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(start = 15.dp))
                        }
                    }
                }
            }
        },
        // The item content for the indices list
        indexedItemContent = { item, isSelected ->
            Text(
                modifier = Modifier
                    .background(color = Color.Transparent),
                color = if (isSelected) Color.Blue else Color.Black,
                text = item.toString(),
                fontSize = 20.sp)
        }
    )
}

@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ExampleIndexedDataLazyColumn(data: List<CustomListItem2>,
                                 indices: List<Char> = ('A'..'Z').toList()) {

    IndexedDataLazyColumn(
        // The list of the indices
        indices = indices,
        // The list of the actual data
        data = data,
        // The modifier is exported for the Column, the one with the indices
        modifier = Modifier
            .background(color = Color.Transparent)
            .height(300.dp),
        // The way to connect the index with a data item (here the index item matches the first letter of the surname)
        predicate = {
            data.indexOfFirst { item ->
                item.surname.startsWith(it.toString(), true) }
        },
        // The list of the main data
        mainItemContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable { },
                elevation = 10.dp
            ) {
                Column {
                    Text(text = data[it].surname,
                        fontSize = 17.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(start = 10.dp))
                    Text(text = data[it].name,
                        fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 15.dp))
                }
            }
        },
        // The item content for the indices list
        indexedItemContent = { item, isSelected ->
            Text(
                modifier = Modifier
                    .background(color = Color.Transparent),
                color = if (isSelected) Color.Blue else Color.Black,
                text = item.toString(),
                fontSize = 20.sp)
        }
    )
}