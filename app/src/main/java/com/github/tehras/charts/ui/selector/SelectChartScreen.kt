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
import com.github.tehras.charts.ui.ChartScreenStatus

@Composable
fun SelectChartScreen() {
    Scaffold(
        topAppBar = { TopAppBar(title = { Text(text = "Select Chart") }) },
        bodyContent = { modifier -> SelectChartScreenContent(modifier) }
    )
}

@Composable
private fun SelectChartScreenContent(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        PieChartRow()
        BarChartRow()
    }
}

@Composable
private fun PieChartRow() {
    Row(modifier = Modifier.padding(horizontal = Margins.horizontal, vertical = Margins.vertical)) {
        Button(
            onClick = { ChartScreenStatus.navigateTo(ChartScreen.Pie) },
            text = { Text(text = "Pie Chart") }
        )
    }
}

@Composable
private fun BarChartRow() {
    Row(modifier = Modifier.padding(horizontal = Margins.horizontal, vertical = Margins.vertical)) {
        Button(
            onClick = { ChartScreenStatus.navigateTo(ChartScreen.Bar) },
            text = { Text(text = "Bar Chart") }
        )
    }
}

@Preview
@Composable
fun SelectChartScreenPreview() = SelectChartScreen()
