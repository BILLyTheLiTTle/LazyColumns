[![](https://jitpack.io/v/BILLyTheLiTTle/LazyColumns.svg)](https://jitpack.io/#BILLyTheLiTTle/LazyColumns)

### DoubleHeaderLazyColumn
#### How to use
To implement the double header `LazyColumn` your data should override the `Item` class:
```kotlin
open class Item(
    /* This String here defines a header */
    open val type: String,
    
    /* This String here defines subheader */
    open val subType: String
)
```
All of the above properties should be filled for the double header `LazyColumn` to work correctly.
To render your data you should call this Composable function:
```kotlin
fun DoubleHeaderLazyColumn(
    /* The data which inherit from Item class */
    data: List<Item>, // -> 
    
    modifier: Modifier,
    
    /* Header Composable */
    headerContent: @Composable (String) -> Unit,
    
    /* Subheader Composable */
    subHeaderContent: @Composable (String) -> Unit,
    
    /* Composable for each item */
    itemContent: @Composable (Item) -> Unit)
)
```

#### Examples
You can find a code example [here](https://github.com/BILLyTheLiTTle/LazyColumns/blob/main/app/src/main/java/com/billythelittle/lazycolumnsexample/doubleheader/ExampleBoubleHeaderList.kt).

If you want to see how it looks like, check this [video](https://youtu.be/VXfqgaCA_6w).

<p align="center">
	<a href="https://www.youtube.com/watch?v=VXfqgaCA_6w" target="_blank">
		<img src="https://img.youtube.com/vi/VXfqgaCA_6w/0.jpg">
	</a>
</p>
