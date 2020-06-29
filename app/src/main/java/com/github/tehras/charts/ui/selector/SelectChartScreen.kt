package com.github.tehras.charts.ui.selector

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import com.github.tehras.charts.theme.Margins
import com.github.tehras.charts.ui.ChartScreen
import com.github.tehras.charts.ui.ChartScreen.*
import com.github.tehras.charts.ui.ChartScreenStatus

@Composable
fun SelectChartScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Select Chart") }) },
        bodyContent = { SelectChartScreenContent() }
    )
}

@Composable
private fun SelectChartScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        ChartRow(text = "Pie Chart", navigateTo = Pie)
        ChartRow(text = "Bar Chart", navigateTo = Bar)
        ChartRow(text = "Line Chart", navigateTo = Line)
    }
}

@Composable
private fun ChartRow(
    text: String,
    navigateTo: ChartScreen
) {
    Row(modifier = Modifier.padding(horizontal = Margins.horizontal, vertical = Margins.vertical)) {
        Button(
            onClick = { ChartScreenStatus.navigateTo(navigateTo) },
            text = { Text(text = text) }
        )
    }
}

@Preview
@Composable
fun SelectChartScreenPreview() = SelectChartScreen()
