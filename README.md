[![](https://jitpack.io/v/BILLyTheLiTTle/LazyColumns.svg)](https://jitpack.io/#BILLyTheLiTTle/LazyColumns)

# LazyColumns

## Description
Here you will find variation of `LazyColumn`s for Jetpack Compose. You can have the List UI you have used to, in a **Jetpack Compose** way eliminating the need of `AndroidView` Composable.

## Compatibility
| LazyColums       | Kotlin | Jetpack Compose  |
|------------------| ------------- | ------------- |
| v0.2.5 and below | 1.5.21  | 1.0.1  |
| v0.2.6 - v0.2.7  | 1.6.10  | 1.1.1  |
| v0.2.8 - v.0.2.9 | 1.7.0  | 1.2.0  |
| v0.3.0 and above | 1.8.0  | 1.4.1  |

## Installation
To include the library in your project you should add it in your root build.gradle or in your settings.gradle the repository:
```kotlin
repositories {
	maven { url 'https://jitpack.io' }
}
```
Next add the dependency in your module:
```kotlin
dependencies {
	 implementation 'com.github.BILLyTheLiTTle:LazyColumns:0.3.2'
}
```

## What is implemented

[Double Header LazyColumn](/Guidelines/Double_Header_LazyColumn.md)


[Indexed LazyColumns](/Guidelines/Indexed_LazyColumn.md)


[LazyColumn With Scrollbar](/Guidelines/LazyColum_with_scrollbar.md)

[Netflix Lazy Screen](/Guidelines/Netflix_Lazy_Screen.md)
