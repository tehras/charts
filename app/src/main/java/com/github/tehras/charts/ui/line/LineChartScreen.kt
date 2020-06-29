package com.github.tehras.charts.ui.line

import androidx.compose.Composable
import androidx.ui.core.Alignment.Companion.CenterHorizontally
import androidx.ui.core.Alignment.Companion.CenterVertically
import androidx.ui.core.Modifier
import androidx.ui.foundation.Border
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.graphics.SolidColor
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.gravity
import androidx.ui.material.*
import androidx.ui.material.icons.Icons.Filled
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.unit.dp
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.theme.Margins.horizontal
import com.github.tehras.charts.theme.Margins.vertical
import com.github.tehras.charts.theme.Margins.verticalLarge
import com.github.tehras.charts.ui.ChartScreenStatus
import com.github.tehras.charts.ui.line.LineChartDataModel.PointDrawerType

@Composable
fun LineChartScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { ChartScreenStatus.navigateHome() }) {
                        Icon(Filled.ArrowBack)
                    }
                },
                title = { Text(text = "Line Chart") }
            )
        },
        bodyContent = { LineChartScreenContent() }
    )
}

@Composable
fun LineChartScreenContent() {
    val lineChartDataModel = LineChartDataModel()

    Column(
        modifier = Modifier.padding(
            horizontal = horizontal,
            vertical = vertical
        )
    ) {
        LineChartRow(lineChartDataModel)
        HorizontalOffsetSelector(lineChartDataModel)
        OffsetProgress(lineChartDataModel)
    }
}

@Composable
fun HorizontalOffsetSelector(lineChartDataModel: LineChartDataModel) {
    val selectedType = lineChartDataModel.pointDrawerType

    Row(
        modifier = Modifier.padding(
            horizontal = horizontal,
            vertical = verticalLarge
        ),
        verticalGravity = CenterVertically
    ) {
        Text("Point Drawer")

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = horizontal, vertical = vertical)
                .gravity(CenterHorizontally)
        ) {
            PointDrawerType.values().forEach { type ->
                val color = if (selectedType == type) {
                    Color.Black
                } else {
                    Color.Transparent
                }

                TextButton(
                    border = Border(2.dp, SolidColor(color)),
                    onClick = { lineChartDataModel.pointDrawerType = type }
                ) {
                    Text(type.name)
                }
            }
        }
    }
}

@Composable
fun OffsetProgress(lineChartDataModel: LineChartDataModel) {
    Row(
        modifier = Modifier.padding(horizontal = horizontal),
        verticalGravity = CenterVertically
    ) {
        Text("Offset")

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
                .gravity(CenterHorizontally)
        ) {
            Slider(
                value = lineChartDataModel.horizontalOffset,
                onValueChange = { lineChartDataModel.horizontalOffset = it },
                valueRange = 0f.rangeTo(25f)
            )
        }
    }
}

@Composable
fun LineChartRow(lineChartDataModel: LineChartDataModel) {
    Box(modifier = Modifier.height(250.dp).fillMaxWidth()) {
        LineChart(
            lineChartData = lineChartDataModel.lineChartData,
            horizontalOffset = lineChartDataModel.horizontalOffset,
            pointDrawer = lineChartDataModel.pointDrawer
        )
    }
}
