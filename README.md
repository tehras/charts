# Compose Charts

<p>
This is an exploratory playground library to figure out how to Draw and animate using Android Jetpack Compose library.
Currently this is using `0.1.0-dev13` library.
</p>
---
<h3>How it looks:</h3>
<br/>

<img src="/assets/pie_chart.gif" width="200">

---

<h3>How to use Pie Chart:</h3>
<br/>

1. Wrap your layout
```kotlin
@Composable
fun MyChartParent() {
     PieChart(pieChartData = PieChartData(
         slices = listOf(Slice(...), Slice(...),....),
         sliceThickness = 25f
     ))
}
```
