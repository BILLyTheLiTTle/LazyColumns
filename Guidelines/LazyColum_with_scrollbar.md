[![](https://jitpack.io/v/BILLyTheLiTTle/LazyColumns.svg)](https://jitpack.io/#BILLyTheLiTTle/LazyColumns)

### LazyColumnWithScrollbar
#### How to use
To implement the `LazyColumn` with a scrollbar you should call this Composable function:
```kotlin
/* The parameters below, are the same (some are missing though) as a regular LazyColumn implementation */
fun <T> LazyColumnWithScrollbar(
    data: List<T>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    
    /* The scrollbar rendering settings */
    settings: LazyColumnScrollbarSettings = LazyColumnScrollbarSettings(),
    
    content: LazyListScope.() -> Unit
)
```
#### Examples
You can find a code example [here](https://github.com/BILLyTheLiTTle/LazyColumns/blob/main/app/src/main/java/com/billythelittle/lazycolumnsexample/scrollbar/ExampleLazyColumnsWithScrollbar.kt).

If you want to see how it looks like, check this [video](https://youtube.com/shorts/YQ6H26Sf6CM).

<p align="center">
	<a href="https://www.youtube.com/watch?v=YQ6H26Sf6CM" target="_blank">
		<img src="https://img.youtube.com/vi/YQ6H26Sf6CM/0.jpg">
	</a>
</p>
