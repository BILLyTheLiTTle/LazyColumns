package com.billythelittle.lazycolumns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/*
 * The data used in the list should override this class.
 * The properties in this class should contain data.
 */
open class Item(
    open val type: String,
    open val subType: String
)

@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun DoubleHeaderLazyColumn(data: List<Item>,
                     modifier: Modifier = Modifier,
                     headerContent: @Composable (String) -> Unit,
                     subHeaderContent: @Composable (String) -> Unit,
                     itemContent: @Composable (Item) -> Unit) {
    // Use state to achieve re-compose
    val listState = rememberLazyListState()
    val header = remember {
        mutableStateOf(data[0].type)
    }

    // Use state to understand if the user is moving to the start or the end of the list
    val firstItemIndex = remember {
        mutableStateOf(0)
    }

    // create a list which is a mirror of the LazyColumn
    val dataList = mutableListOf<Item>()
    val groupHeader = data.groupBy { it.type }
    groupHeader.forEach { (header, items) ->
        dataList.add(Item(type = header, ""))
        val groupSubHeader = items.groupBy { it.subType }
        groupSubHeader.forEach { (subheader, items2) ->
            dataList.add(Item(type = header, subType = subheader))
            dataList.addAll(items2)
        }
    }

    val mainGroup = data.groupBy { it.type }
    Column(modifier = modifier) {
        // This header's content will be updated while scrolling
        headerContent(header.value)
        LazyColumn(state = listState) {
            mainGroup.forEach { (type, groupedData) ->
                stickyHeader {
                    // This header visibility will be updated while scrolling
                    AnimatedVisibility(visible = header.value != type,
                        enter= expandVertically(),
                        exit= shrinkVertically(animationSpec = spring(stiffness = 150f))
                    ) {
                        headerContent(type)
                    }
                }
                val subGroup = groupedData.groupBy { it.subType }
                subGroup.forEach { (subType, subGroupedData)->
                    stickyHeader {
                        // Create only the subheader item
                        subHeaderContent(subType)
                    }

                    items(items = subGroupedData) {
                        //listState.firstVisibleItemIndex.toString()
                        itemContent(it)

                        /* This is doing the magic for visibility and content of the above headers
                            depending on the user scrolling action
                         */
                        if (listState.firstVisibleItemIndex < firstItemIndex.value) { // move to start
                            if (listState.firstVisibleItemIndex > 0) {
                                header.value = dataList[listState.firstVisibleItemIndex - 1].type
                            }
                        }
                        else if (listState.firstVisibleItemIndex > firstItemIndex.value) { // move to the end
                            /*
                            When you scroll to start and you go to the point where the stickyHeader should
                            become visible by default it behaves like scrolling to the end. When this happens
                            you enter in this else-if block but the firstVisibleItemIndex is increased by 2
                            (you added a new item, the stickyHeader). So, do the following action whenever
                            all these things did not happen.
                             */
                            if (listState.firstVisibleItemIndex - firstItemIndex.value != 2){
                                header.value = dataList[listState.firstVisibleItemIndex].type
                            }
                        }
                        firstItemIndex.value = listState.firstVisibleItemIndex
                    }
                }
            }
        }
    }
}