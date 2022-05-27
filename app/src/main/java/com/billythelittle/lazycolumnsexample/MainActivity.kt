package com.billythelittle.lazycolumnsexample

import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isFinite
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import coil.annotation.ExperimentalCoilApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.billythelittle.lazycolumnsexample.doubleheader.ExampleDoubleHeaderList
import com.billythelittle.lazycolumnsexample.indexed.ExampleIndexedDataLazyColumn
import com.billythelittle.lazycolumnsexample.indexed.ExampleIndexedLazyColumn
import com.billythelittle.lazycolumnsexample.ui.theme.LazyColumnsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalCoilApi::class, ExperimentalFoundationApi::class)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    ExampleDoubleHeaderList(getTheData())
//                    ExampleIndexedLazyColumn(getTheIndexedData())
//                    ExampleIndexedDataLazyColumn(getTheIndexedData())
                    ExampleScrollableDataLazyColumn((1..100).toList())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ExampleScrollableDataLazyColumn(data: List<Int>) {
    val listState = rememberLazyListState()
    val coroutineContext = rememberCoroutineScope()
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

    BoxWithConstraints() {
        LazyColumn(state = listState,
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
            if (!listState.isScrollInProgress) {
                isScrollbarVisible.value = false
                if (listState.layoutInfo.visibleItemsInfo.isNotEmpty()) {
                    firstVisibleItem.value = listState.layoutInfo.visibleItemsInfo.first().index
                }
            } else if (listState.isScrollInProgress && isUserScrollingLazyColumn.value) {
                isScrollbarVisible.value = true
                if (heightInPixels.value != 0F) {

                    if (firstVisibleItem.value > listState.layoutInfo.visibleItemsInfo.first().index) {
                        /* The items which could be rendered should not be taken under account
                   otherwise you are going to show the last rendered items before
                   the scrollbar reaches the bottom.
                   Change the renderedItemsNumberPerScroll = 0 and scroll to the bottom
                   and you will understand.
                    */
                        // Scroll to upper start of list
                        val renderedItemsNumberPerScroll =
                            listState.layoutInfo.visibleItemsInfo.size - 2
                        val index = listState.layoutInfo.visibleItemsInfo.first().index
                        val indexPercentage = ((100 * index) / data.lastIndex)

                        val yMaxValue = heightInPixels.value - heightInPixels.value / 3F

                        val calculatedOffsetY = ((yMaxValue * indexPercentage) / 100)

                        offsetY.value = calculatedOffsetY
                    } else { // scroll to bottom end of list
                        /* The items which could be rendered should not be taken under account
                   otherwise you are going to show the last rendered items before
                   the scrollbar reaches the bottom.
                   Change the renderedItemsNumberPerScroll = 0 and scroll to the bottom
                   and you will understand.
                    */
                        val renderedItemsNumberPerScroll =
                            listState.layoutInfo.visibleItemsInfo.size - 2
                        val index = listState.layoutInfo.visibleItemsInfo.last().index
                        val indexPercentage = ((100 * (index)) / data.lastIndex)

                        val yMaxValue = heightInPixels.value - heightInPixels.value / 3F

                        val calculatedOffsetY = ((yMaxValue * indexPercentage) / 100)

                        offsetY.value = calculatedOffsetY
                    }

                }
            }
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
//        AnimatedVisibility(
//            visible = listState.isScrollInProgress,
//            enter = fadeIn(
//                //initialOffsetX = { -300 }, // small slide 300px
//                animationSpec = tween(
//                    durationMillis = 200,
//                    easing = LinearEasing // interpolator
//                )
//            ),
//            exit = fadeOut(
//                //targetOffsetX = { 100 },
//                    animationSpec = tween(
//                    durationMillis = 1000,
//            easing = LinearEasing
//        )
//        ),
//            modifier = Modifier.align(Alignment.CenterEnd)
//        ) {
            val color = Color.DarkGray
            Canvas(modifier = Modifier
                .width(15.dp)
                .height(maxHeight)
                .align(Alignment.CenterEnd)
                .background(Color.Transparent)
                .pointerInput(Unit) {
                    heightInPixels.value = maxHeight.toPx()
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        isUserScrollingLazyColumn.value = false
                        if (dragAmount.y > 0) { // drag slider down -> GREEN
//                        color = Color.Green
                            if (offsetY.value >= (maxHeight.toPx() - maxHeight.toPx() / 3F)) {
                                // Bottom End
                                offsetY.value = maxHeight.toPx() - maxHeight.toPx() / 3F
                                coroutineContext.launch {
                                    listState.scrollToItem(data.lastIndex)
                                }
                            } else {
                                offsetY.value = offsetY.value + dragAmount.y
                            }
                        } else { // drag slider up -> BLACK
//                        color = Color.Black
                            if (offsetY.value <= 0f) {
                                // Top End
                                offsetY.value = 0F
                                coroutineContext.launch {
                                    listState.scrollToItem(0)
                                }
                            } else {
                                offsetY.value = offsetY.value + dragAmount.y
                            }
                        }
                        val yMaxValue = maxHeight.toPx() - maxHeight.toPx() / 3F
                        val yPercentage = (100 * offsetY.value) / yMaxValue

                        /* The items which could be rendered should not be taken under account
                        otherwise you are going to show the last rendered items before
                        the scrollbar reaches the bottom.
                        Change the renderedItemsNumberPerScroll = 0 and scroll to the bottom
                        and you will understand.
                         */
                        val renderedItemsNumberPerScroll =
                            listState.layoutInfo.visibleItemsInfo.size - 2
                        val index =
                            (((data.lastIndex - renderedItemsNumberPerScroll) * yPercentage) / 100).toInt()


                        coroutineContext.launch {
                            if (index > 0) {
                                listState.scrollToItem(index)
                            }
                        }
                    }
                }
            ) {
                drawRoundRect(
                    topLeft = Offset(0f, offsetY.value),
                    color = color,
                    size = Size(size.width / 2F, maxHeight.toPx() / 3F),
                    cornerRadius = CornerRadius(20F, 20F)
                )
            }
//        }
    }
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex = remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset = remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex.value != firstVisibleItemIndex) {
                previousIndex.value > firstVisibleItemIndex
            } else {
                previousScrollOffset.value >= firstVisibleItemScrollOffset
            }.also {
                previousIndex.value = firstVisibleItemIndex
                previousScrollOffset.value = firstVisibleItemScrollOffset
            }
        }
    }.value
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