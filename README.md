[![](https://jitpack.io/v/BILLyTheLiTTle/LazyColumns.svg)](https://jitpack.io/#BILLyTheLiTTle/LazyColumns)

# LazyColumns
Here you will find variation of `LazyColumn`s for Jetpack Compose.

## Compatibility
| LazyColums  | Kotlin | Jetpack Compose  |
| ------------- | ------------- | ------------- |
| v0.2.5 and below  | 1.5.21  | 1.0.1  |
| v0.2.6 and above  | 1.6.10  | 1.1.1  |

## Include Library
To include the library in your project you should add it in your root build.gradle or in your settings.gradle the repository:
```kotlin
repositories {
	maven { url 'https://jitpack.io' }
}
```
Next add the dependency in your module:
```kotlin
dependencies {
	 implementation 'com.github.BILLyTheLiTTle:LazyColumns:0.2.7'
}
```

## What is implemented

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

### IndexedLazyColumns
#### How to use
To implement the indexed `LazyColumn` you should call:
 - this Composable function in case you have only the data to render
```kotlin
fun <T> IndexedDataLazyColumn(
    /* The indices list */
    indices: List<T>,
    
    /* The main data list */
    data: List<T>,
    
    /* The modifier for the main items LazyColumn */
    mainModifier: Modifier = Modifier,
    
    /* The modifier for the indices LazyColumn */
    indicesModifier: Modifier = Modifier,
    
    /* This lambda here, is the connection between indices and data. 
    You are free to write your own logic, but you must be careful to always 
    add as parameter an item from indices list and return the index of an item from data.
    */
    predicate: (T) -> Int,
    
    /* The way to connect the a data item with specific index 
    (here the first letter of the surname matches the index item).
    If this parameter is not provided (null) there is no connection from data list to the indices list.
    */
    reversePredicate: ((LazyListState) -> Int)? = null,
    
    /* The alignment settings for the indices list */
    settings: IndexedLazyColumnsSettings = IndexedLazyColumnsSettings(),
    
    /* The Composable for the main items LazyColumn */
    mainItemContent: @Composable LazyItemScope.(Int) -> Unit,
    
    /* The Composable for the indices LazyColumn.
    T: The index item to be rendered
    Boolean: Whether or not the specific index item is selected by the user
    Boolean: Whether or not the specific index item is selected by the user has a match on the data list
    */
    indexedItemContent: @Composable (T, Boolean, Boolean) -> Unit
)
```
 - or this in case you want to create your own `LazyColumn` implementation for the main data
```kotlin
fun <T> IndexedLazyColumn(
    /* The indices list */
    indices: List<T>,
    
    /* This value will be connection between your own LazyColumn with the indices */
    itemsListState: LazyListState,
    
    /* The modifier for the main items LazyColumn */
    mainModifier: Modifier = Modifier,
    
    /* The modifier for the indices LazyColumn */
    indicesModifier: Modifier = Modifier,
    
    /* This lambda here, is the connection between indices and data. 
    You are free to write your own logic, but you must be careful to always 
    add as parameter an item from indices list and return the index of an item from data.
    */
    predicate: (T) -> Int,
    
    /* The way to connect the a data item with specific index 
    (here the first letter of the surname matches the index item).
    If this parameter is not provided (null) there is no connection from data list to the indices list.
    */
    reversePredicate: ((LazyListState) -> Int)? = null,
    
    /* The alignment settings for the indices list */
    settings: IndexedLazyColumnsSettings = IndexedLazyColumnsSettings(),
    
    /* The Composable where you put your own LazyColumn implementation */
    lazyColumnContent: @Composable () -> Unit,
    
    /* The Composable for the indices LazyColumn.
    T: The index item to be rendered
    Boolean: Whether or not the specific index item is selected by the user
    Boolean: Whether or not the specific index item is selected by the user has a match on the data list
    */
    indexedItemContent: @Composable (T, Boolean, Boolean) -> Unit
)
```
#### Examples
You can find a code example [here](https://github.com/BILLyTheLiTTle/LazyColumns/blob/main/app/src/main/java/com/billythelittle/lazycolumnsexample/indexed/ExampleIndexedLazyColumns.kt).

If you want to see how it looks like, check this [video](https://youtu.be/-LvYbSgeMwU).

<p align="center">
	<a href="https://www.youtube.com/watch?v=-LvYbSgeMwU" target="_blank">
		<img src="https://img.youtube.com/vi/-LvYbSgeMwU/0.jpg">
	</a>
</p>

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
You can find an example [here](https://github.com/BILLyTheLiTTle/LazyColumns/blob/main/app/src/main/java/com/billythelittle/lazycolumnsexample/scrollbar/ExampleLazyColumnsWithScrollbar.kt).

If you want to see how it looks like, check this [video](https://youtube.com/shorts/YQ6H26Sf6CM).

<p align="center">
	<a href="https://www.youtube.com/watch?v=YQ6H26Sf6CM" target="_blank">
		<img src="https://img.youtube.com/vi/YQ6H26Sf6CM/0.jpg">
	</a>
</p>
