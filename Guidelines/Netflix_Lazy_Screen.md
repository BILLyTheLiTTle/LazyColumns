[![](https://jitpack.io/v/BILLyTheLiTTle/LazyColumns.svg)](https://jitpack.io/#BILLyTheLiTTle/LazyColumns)

### NetflixLazyScreen
<img src="./assets/netflix_clone_compose.gif" width="250"/>

#### How to use
You have to implement the whole screen instead of `LazyColumn`:
- The `PrimaryAppBar` has the same parameters as the `TopAppBar` from Jetpack Compose.
- The `SecondaryAppBar` the scrolling area which looks like the AppBar above. It has the same parameters as the `LazyRow` from Jetpack Compose.
- The `MainContent` has the parameters of a `LazyColumn` and here you have to add almost of the content of the screen.

Combine all of the above with a `Modifier` and a `NetflixLazyScreenSettings` in which you can modify the height of the Toolbar and you have the screen!
```kotlin
fun NetflixLazyScreen(
    modifier: Modifier = Modifier,
    settings: NetflixLazyScreenSettings = NetflixLazyScreenSettings(),
    primaryAppBar: @Composable () -> Unit,
    secondaryAppBar: @Composable () -> Unit,
    content: @Composable () -> Unit
)
```
#### Examples
You can find a code example [here](https://github.com/BILLyTheLiTTle/LazyColumns/blob/2a4ea83c4b281175ed281807490bf94dc09b85c6/app/src/main/java/com/billythelittle/lazycolumnsexample/netflixscreen/ExampleNetflixLazyScreen.kt).
