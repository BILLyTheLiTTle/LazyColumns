package com.billythelittle.lazycolumns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


data class IndexedLazyColumnsSettings(
    var indicesPosition: Alignment = Alignment.CenterEnd
)

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun <T> IndexedLazyColumn(
    indices: List<T>,
    itemsListState: LazyListState,
    mainModifier: Modifier = Modifier,
    indicesModifier: Modifier = Modifier,
    predicate: (T) -> Int,
    settings: IndexedLazyColumnsSettings = IndexedLazyColumnsSettings(),
    lazyColumnContent: @Composable () -> Unit,
    indexedItemContent: @Composable (T, Boolean) -> Unit
) {
    val coroutineContext = rememberCoroutineScope()
    val indexState = rememberLazyListState()
    val shouldUpdateSelection = remember {
        mutableStateOf(false)
    }
    val selectedIndex = remember {
        mutableStateOf(0)
    }

    Box(modifier = mainModifier, contentAlignment = settings.indicesPosition) {
        Column() {
            lazyColumnContent()
        }

        LazyColumn(
            state = indexState,
            horizontalAlignment = Alignment.End,
            modifier = indicesModifier
        ) {
            if (!indexState.isScrollInProgress && shouldUpdateSelection.value) {
                val index = selectIndexItem(indexState)

                scrollMainListBasedOnIndex(
                    coroutineContext, predicate,
                    indices, itemsListState,
                    selectedIndex, index
                )

                shouldUpdateSelection.value = false
            } else if (indexState.isScrollInProgress) {
                shouldUpdateSelection.value = true
            }

            itemsIndexed(indices) { index, item ->
                Box(modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        scrollMainListBasedOnIndex(
                            coroutineContext, predicate,
                            indices, itemsListState,
                            selectedIndex, index
                        )
                    }) {
                    indexedItemContent(item, index == selectedIndex.value)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun <T> IndexedDataLazyColumn(
    indices: List<T>,
    data: List<T>,
    mainModifier: Modifier = Modifier,
    indicesModifier: Modifier = Modifier,
    predicate: (T) -> Int,
    settings: IndexedLazyColumnsSettings = IndexedLazyColumnsSettings(),
    mainItemContent: @Composable LazyItemScope.(Int) -> Unit,
    indexedItemContent: @Composable (T, Boolean) -> Unit
) {
    val coroutineContext = rememberCoroutineScope()
    val indexState = rememberLazyListState()
    val itemsState = rememberLazyListState()
    val shouldUpdateSelection = remember {
        mutableStateOf(false)
    }
    val selectedIndex = remember {
        mutableStateOf(0)
    }

    Box(modifier = mainModifier, contentAlignment = settings.indicesPosition) {
        Column() {
            LazyColumn(state = itemsState) {
                itemsIndexed(data) { index, item ->
                    mainItemContent(index)
                }
            }
        }

        LazyColumn(
            state = indexState,
            horizontalAlignment = Alignment.End,
            modifier = indicesModifier
        ) {
            if (!indexState.isScrollInProgress && shouldUpdateSelection.value) {
                val index = selectIndexItem(indexState)

                scrollMainListBasedOnIndex(
                    coroutineContext, predicate,
                    indices, itemsState,
                    selectedIndex, index
                )

                shouldUpdateSelection.value = false
            } else if (indexState.isScrollInProgress) {
                shouldUpdateSelection.value = true
            }

            itemsIndexed(indices) { index, item ->
                Box(modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        scrollMainListBasedOnIndex(
                            coroutineContext, predicate,
                            indices, itemsState,
                            selectedIndex, index
                        )
                    }) {
                    indexedItemContent(item, index == selectedIndex.value)
                }
            }
        }
    }
}

private fun <T> scrollMainListBasedOnIndex(
    coroutineContext: CoroutineScope,
    predicate: (T) -> Int,
    indices: List<T>,
    itemsListState: LazyListState,
    selectedIndex: MutableState<Int>,
    index: Int
) {
    coroutineContext.launch {
        val itemIndex = predicate(indices[index])
        if (itemIndex >= 0) {
            itemsListState.scrollToItem(itemIndex)
        }
        selectedIndex.value = index
    }
}

private fun selectIndexItem(indexState: LazyListState): Int {
    val list = indexState.layoutInfo.visibleItemsInfo
    val startIndex = list.first().index
    val lastIndex = list.last().index
    return startIndex + ((lastIndex - startIndex) / 2)
}