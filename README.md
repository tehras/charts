# Compose Charts

This is an exploratory playground library to figure out how to Draw and animate using Android Jetpack Compose library.

[![Release](https://jitpack.io/v/tehras/charts.svg)]
(https://jitpack.io/#tehras/charts)

## Implementation:

build.gradle (app)
```groovy
dependecies {
    implementation 'com.github.tehras:charts:0.2.1-alpha'
}
```

settings.gradle
```
repositories {
    google()
    mavenCentral()
    maven { url 'https://jitpack.io'}
}
```

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
        animation = simpleChartAnimation(),
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
        animation = simpleChartAnimation(),
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
        linesChartData = listOf(LineChartData(points = listOf(LineChartData.Point(1f,"Label 1"), ...))),
        // Optional properties.
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(),
        lineDrawer = SolidLineDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        horizontalOffset = 5f,
        labels = listOf("label 1" ...) 
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
