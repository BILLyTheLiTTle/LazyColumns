package com.billythelittle.lazycolumns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class LazyColumnScrollbarSettings(
    var thumbColor: Color = Color.DarkGray,
    var trailColor: Color = Color.Transparent,
    var thumbWidth: ThumbWidth = ThumbWidth.MEDIUM,
    var thumbHeight: ThumbHeight = ThumbHeight.MEDIUM,
) {


    enum class ThumbHeight(val value: Float) {
        X_SMALL(6F),
        SMALL(5F),
        MEDIUM(4F),
        LARGE(3F),
        X_LARGE(2F),
    }

    enum class ThumbWidth(val value: Dp) {
        X_SMALL(5.dp),
        SMALL(10.dp),
        MEDIUM(15.dp),
        LARGE(25.dp),
        X_LARGE(30.dp),
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun <T> LazyColumnWithScrollbar(
    data: List<T>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
//                            reverseLayout: Boolean = false,
//                            verticalArrangement: Arrangement.Vertical =
//                                if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    settings: LazyColumnScrollbarSettings = LazyColumnScrollbarSettings(),
    content: LazyListScope.() -> Unit
) {
    val coroutineContext = rememberCoroutineScope()
    val animationCoroutineContext = rememberCoroutineScope()

    val offsetY = remember { mutableStateOf(0f) }
    val isUserScrollingLazyColumn = remember {
        mutableStateOf(true)
    }
    val heightInPixels = remember {
        mutableStateOf(0F)
    }
    val firstVisibleItem = remember {
        mutableStateOf(0)
    }
    val isScrollbarVisible = remember {
        mutableStateOf(false)
    }

    BoxWithConstraints(modifier = modifier) {
        LazyColumn(state = state,
            contentPadding = contentPadding,
//            reverseLayout = reverseLayout,
//        verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            flingBehavior = flingBehavior,
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(onPress = {
                    isUserScrollingLazyColumn.value = true
                    heightInPixels.value = maxHeight.toPx()
                },
                    onTap = {
                        isUserScrollingLazyColumn.value = true
                        heightInPixels.value = maxHeight.toPx()
                    })
            }
        ) {
            if (!state.isScrollInProgress) {
                isUserScrollingLazyColumn.value = true
                hideScrollbar(animationCoroutineContext, isScrollbarVisible)

                if (state.layoutInfo.visibleItemsInfo.isNotEmpty()) {
                    firstVisibleItem.value = state.layoutInfo.visibleItemsInfo.first().index
                }
            } else if (state.isScrollInProgress && isUserScrollingLazyColumn.value) {
                showScrollbar(animationCoroutineContext, isScrollbarVisible)

                if (heightInPixels.value != 0F) {

                    if (firstVisibleItem.value > state.layoutInfo.visibleItemsInfo.first().index || // Scroll to upper start of list
                        state.layoutInfo.visibleItemsInfo.first().index == 0 // Reached the upper start of list
                    ) {
                        if (state.layoutInfo.visibleItemsInfo.first().index == 0) {
                            offsetY.value = 0F
                        } else {
                            offsetY.value = calculateScrollbarOffsetY(
                                state,
                                data.size,
                                heightInPixels,
                                settings.thumbHeight.value
                            )
                        }
                    } else { // scroll to bottom end of list or reach the bottom end of the list
                        if (state.layoutInfo.visibleItemsInfo.last().index == data.lastIndex) {
                            offsetY.value =
                                heightInPixels.value - heightInPixels.value / settings.thumbHeight.value
                        } else {
                            offsetY.value = calculateScrollbarOffsetY(
                                state,
                                data.size,
                                heightInPixels,
                                settings.thumbHeight.value
                            )
                        }
                    }

                }
            }
            content()
        }
        if (state.layoutInfo.visibleItemsInfo.size < data.size) {
            AnimatedVisibility(
                visible = isScrollbarVisible.value,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        delayMillis = 1000,
                        durationMillis = 1000,
                        easing = LinearEasing
                    )
                ),
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Canvas(modifier = Modifier
                    .width(settings.thumbWidth.value)
                    .height(maxHeight)
                    .align(Alignment.CenterEnd)
                    .background(settings.trailColor)
                    .pointerInput(Unit) {
                        heightInPixels.value = maxHeight.toPx()
                        detectDragGestures { change, dragAmount ->
                            change.consumeAllChanges()

                            showScrollbar(animationCoroutineContext, isScrollbarVisible)

                            isUserScrollingLazyColumn.value = false
                            if (dragAmount.y > 0) { // drag slider down
                                if (offsetY.value >= (maxHeight.toPx() - maxHeight.toPx() / settings.thumbHeight.value)) { // Bottom End
                                    offsetY.value =
                                        maxHeight.toPx() - maxHeight.toPx() / settings.thumbHeight.value
                                    coroutineContext.launch {
                                        state.scrollToItem(data.lastIndex)
                                    }
                                } else {
                                    offsetY.value = offsetY.value + dragAmount.y
                                }
                            } else { // drag slider up
                                if (offsetY.value <= 0f) { // Top Start
                                    offsetY.value = 0F
                                    coroutineContext.launch {
                                        state.scrollToItem(0)
                                    }
                                } else {
                                    offsetY.value = offsetY.value + dragAmount.y
                                }
                            }
                            val yMaxValue =
                                maxHeight.toPx() - maxHeight.toPx() / settings.thumbHeight.value
                            val yPercentage = (100 * offsetY.value) / yMaxValue

                            /* The items which could be rendered should not be taken under account
                            otherwise you are going to show the last rendered items before
                            the scrollbar reaches the bottom.
                            Change the renderedItemsNumberPerScroll = 0 and scroll to the bottom
                            and you will understand.
                             */
                            val renderedItemsNumberPerScroll =
                                state.layoutInfo.visibleItemsInfo.size - 2
                            val index =
                                (((data.lastIndex - renderedItemsNumberPerScroll) * yPercentage) / 100).toInt()

                            coroutineContext.launch {
                                if (index > 0) {
                                    state.scrollToItem(index)
                                }
                            }
                        }
                    }
                ) {
                    drawRoundRect(
                        topLeft = Offset(size.width / 4F, offsetY.value),
                        color = settings.thumbColor,
                        size = Size(size.width / 2F, maxHeight.toPx() / settings.thumbHeight.value),
                        cornerRadius = CornerRadius(20F, 20F)
                    )
                }
            }
        }
    }
}

private fun hideScrollbar(coroutineScope: CoroutineScope, state: MutableState<Boolean>) {
    coroutineScope.launch {
        state.value = false
    }
}

private fun showScrollbar(coroutineScope: CoroutineScope, state: MutableState<Boolean>) {
    coroutineScope.launch {
        state.value = true
    }
}

/* The items which are already shown on screen should not be taken
for calculations because they are already on screen!
You have to calculate the items remaining off screen as the 100%
of the data and match this percentage with the distance travelled
by the scrollbar.
*/
private fun calculateScrollbarOffsetY(
    state: LazyListState, dataSize: Int,
    height: MutableState<Float>,
    thumbHeight: Float
): Float {
    val renderedItemsNumberPerScroll =
        state.layoutInfo.visibleItemsInfo.size - 2
    val itemsToScroll = dataSize - renderedItemsNumberPerScroll
    val index = state.layoutInfo.visibleItemsInfo.first().index
    val indexPercentage = ((100 * index) / itemsToScroll)

    val yMaxValue = height.value - height.value / thumbHeight

    return ((yMaxValue * indexPercentage) / 100)
}