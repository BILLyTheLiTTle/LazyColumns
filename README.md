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
	 implementation 'com.github.BILLyTheLiTTle:LazyColumns:0.2.4'
}
```

## What is implemented

### DoubleHeaderLazyColumn
To implement the double header `LazyColumn` your data should override the `Item` class
```kotlin
open class Item(
    open val type: String,
    open val subType: String
)
```
and you should call this Composable function
```kotlin
fun DoubleHeaderLazyColumn(data: List<Item>,
                     modifier: Modifier,
                     headerContent: @Composable (String) -> Unit,
                     subHeaderContent: @Composable (String) -> Unit,
                     itemContent: @Composable (Item) -> Unit)
```
You can find a code example [here](https://github.com/BILLyTheLiTTle/LazyColumns/blob/main/app/src/main/java/com/billythelittle/lazycolumnsexample/doubleheader/ExampleBoubleHeaderList.kt).

If you want to see how it looks like check this [video](https://youtu.be/VXfqgaCA_6w).

### IndexedLazyColumns
To implement the indexed `LazyColumn` you should call:
 - this Composable function in case you have only the data to render
```kotlin
fun <T> IndexedDataLazyColumn(
    indices: List<T>,
    data: List<T>,
    mainModifier: Modifier = Modifier,
    indicesModifier: Modifier = Modifier,
    predicate: (T) -> Int,
    mainItemContent: @Composable LazyItemScope.(Int) -> Unit,
    indexedItemContent: @Composable (T, Boolean) -> Unit
)
```
 - or this in case you want to create your own `LazyColumn` implementation for the main data
This Composable function in case you have only the data to render
```kotlin
fun <T> IndexedLazyColumn(
    indices: List<T>,
    itemsListState: LazyListState,
    mainModifier: Modifier = Modifier,
    indicesModifier: Modifier = Modifier,
    predicate: (T) -> Int,
    lazyColumnContent: @Composable () -> Unit,
    indexedItemContent: @Composable (T, Boolean) -> Unit
)
```
You can find a code example [here](https://github.com/BILLyTheLiTTle/LazyColumns/blob/main/app/src/main/java/com/billythelittle/lazycolumnsexample/indexed/ExampleIndexedLazyColumns.kt).

If you want to see how it looks like check this [video](https://youtu.be/-LvYbSgeMwU)

### LazyColumnWithScrollbar
The guide will be published soon...
