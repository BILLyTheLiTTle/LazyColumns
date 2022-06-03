package com.billythelittle.lazycolumnsexample.indexed

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.billythelittle.lazycolumns.IndexedDataLazyColumn
import com.billythelittle.lazycolumns.IndexedLazyColumn
import com.billythelittle.lazycolumns.IndexedLazyColumnsSettings
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
        // The modifier is exported for the Column, the one with the main items
        mainModifier = Modifier.height(600.dp),
        // The modifier is exported for the Column, the one with the indices
        indicesModifier = Modifier
            .background(color = Color.Transparent)
            .height(300.dp),
        // The way to connect the index with a data item (here the index item matches the first letter of the surname)
        predicate = {
            data.indexOfFirst { item ->
                item.surname.startsWith(it.toString(), true)
            }
        },
        // The way to connect the a data item with specific index (here the first letter of the surname matches the index item)
        reversePredicate = {
            val firstChar = data[lazyListState.layoutInfo.visibleItemsInfo.first().index].surname[0]
            indices.indexOf(firstChar)
        },
        // The alignment settings for the indices list
        settings = IndexedLazyColumnsSettings(Alignment.BottomEnd),
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
        /* The item content for the indices list
        item: The index item to be rendered
        isSelected: Whether or not the specific index item is selected by the user
        isSelectedItemExist: Whether or not the specific index item is selected by the user has
        a match on the data list
         */
        indexedItemContent = { item, isSelected, isSelectedItemExist ->
            Text(
                modifier = Modifier
                    .background(color = Color.Transparent),
                color = if (isSelected) {
                    if (isSelectedItemExist) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                } else {
                    Color.Black
                },
                text = item.toString(),
                fontSize = 20.sp
            )
        }
    )
}

@ExperimentalComposeUiApi
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
        // The modifier is exported for the Column, the one with the main items
        mainModifier = Modifier.height(600.dp),
        // The modifier is exported for the Column, the one with the indices
        indicesModifier = Modifier
            .background(color = Color.Transparent)
            .height(300.dp),
        // The way to connect the index with a data item (here the index item matches the first letter of the surname)
        predicate = {
            data.indexOfFirst { item ->
                item.surname.startsWith(it.toString(), true) }
        },
        // The way to connect the a data item with specific index (here the first letter of the surname matches the index item)
        reversePredicate = {
            val firstChar = data[it.layoutInfo.visibleItemsInfo.first().index].surname[0]
            indices.indexOf(firstChar)
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
        /* The item content for the indices list
        item: The index item to be rendered
        isSelected: Whether or not the specific index item is selected by the user
        isSelectedItemExist: Whether or not the specific index item is selected by the user has
        a match on the data list
         */
        indexedItemContent = { item, isSelected, isSelectedItemExist ->
            Text(
                modifier = Modifier
                    .background(color = Color.Transparent),
                color = if (isSelected) {
                    if (isSelectedItemExist) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                } else {
                    Color.Black
                },
                text = item.toString(),
                fontSize = 20.sp
            )
        }
    )
}