package com.github.tehras.charts.ui.line

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.height
import androidx.ui.layout.padding
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons.Filled
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.unit.dp
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.theme.Margins
import com.github.tehras.charts.ui.ChartScreenStatus

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
            horizontal = Margins.horizontal,
            vertical = Margins.vertical
        )
    ) {
        LineChartRow(lineChartDataModel)
    }
}

@Composable
fun LineChartRow(lineChartDataModel: LineChartDataModel) {
    Box(modifier = Modifier.height(250.dp).fillMaxWidth()) {
        LineChart(lineChartData = lineChartDataModel.lineChartData)
    }
}
