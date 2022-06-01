[![](https://jitpack.io/v/BILLyTheLiTTle/LazyColumns.svg)](https://jitpack.io/#BILLyTheLiTTle/LazyColumns)

# LazyColumns
Here you will find variation of `LazyColumn`s for Jetpack Compose.

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
	 implementation 'com.github.BILLyTheLiTTle:LazyColumns:0.2.5'
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
    
    /* The Composable for the main items LazyColumn */
    mainItemContent: @Composable LazyItemScope.(Int) -> Unit,
    
    /* The Composable for the indices LazyColumn */
    indexedItemContent: @Composable (T, Boolean) -> Unit
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
    
    /* The Composable where you put your own LazyColumn implementation */
    lazyColumnContent: @Composable () -> Unit,
    
    /* The Composable for the indices LazyColumn */
    indexedItemContent: @Composable (T, Boolean) -> Unit
)
```
#### Examples
You can find a code example [here](https://github.com/BILLyTheLiTTle/LazyColumns/blob/main/app/src/main/java/com/billythelittle/lazycolumnsexample/indexed/ExampleIndexedLazyColumns.kt).

If you want to see how it looks like, check this [video](https://youtu.be/-LvYbSgeMwU).

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
    content: LazyListScope.() -> Unit
)
```
#### Examples
You can find an example [here](https://github.com/BILLyTheLiTTle/LazyColumns/blob/main/app/src/main/java/com/billythelittle/lazycolumnsexample/scrollbar/ExampleLazyColumnsWithScrollbar.kt).

If you want to see how it looks like, check this [video](https://youtube.com/shorts/YQ6H26Sf6CM).
