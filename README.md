# Compose Charts

<p>
This is an exploratory playground library to figure out how to Draw and animate using Android Jetpack Compose library.
Currently this is using `0.1.0-dev13` library.
</p>

## How it looks:

<img src="/assets/pie_chart.gif" width="200"> <img src="/assets/bar_chart.gif" width="200">

## How to use Pie Chart:

```kotlin
@Composable
fun MyChartParent() {
     PieChart(pieChartData = PieChartData(
         slices = listOf(Slice(...), Slice(...),....),
         sliceThickness = 25f
     ))
}
```

## How to use Bar Chart:

```kotlin
@Composable
fun MyPieChartParent() {
    BarChartData(
        bars = listOf(Bar(label = "Bar Label", value = 100f, color = Color.Red),),
        // Optional value label configs.
        valueLabelFormat = LabelFormat(),
        yAxis = YAxis(),
        xAxis = XAxis()
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
