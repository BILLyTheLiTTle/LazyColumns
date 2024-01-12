[![](https://jitpack.io/v/BILLyTheLiTTle/LazyColumns.svg)](https://jitpack.io/#BILLyTheLiTTle/LazyColumns)

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