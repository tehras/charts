# Compose Charts

This is an exploratory playground library to figure out how to Draw and animate using Android Jetpack Compose library.
Currently this is using `0.1.0-dev15` library.

## How it looks:

<img src="/assets/pie_chart.gif" width="200"> <img src="/assets/bar_chart.gif" width="200"> <img src="/assets/line_chart.gif" width="200">

## How to use Pie Chart:

```kotlin
@Composable
fun MyChartParent() {
    PieChart(
        pieChartData = PieChartData(listOf(Slice(...), Slice(...),....)),
        // Optional properties.
        modifier = Modifier.fillMaxSize(),
        animation = SimpleChartAnimation,
        sliceDrawer = SimpleSliceDrawer()
    )
}
```

## How to use Bar Chart:

```kotlin
@Composable
fun MyBarChartParent() {
    fun BarChart(
        barChartData = BarChartData(bars = listOf(Bar(label = "Bar Label", value = 100f, color = Color.Red)),
        // Optional properties.
        modifier = Modifier.fillMaxSize(),
        animation = SimpleChartAnimation,
        barDrawer = SimpleBarDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        labelDrawer = SimpleValueDrawer()
    ) 
}
```

## How to use Line Chart:

```kotlin
@Composable
fun MyLineChartParent() {
    LineChart(
        lineChartData: LineChartData,
        // Optional properties.
        modifier = Modifier.fillMaxSize(),
        animation = SimpleChartAnimation,
        pointDrawer = FilledCircularPointDrawer(),
        lineDrawer = SolidLineDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        horizontalOffset = 5f
    )
}
```

## License

    Copyright 2020 Taras Koshkin.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
