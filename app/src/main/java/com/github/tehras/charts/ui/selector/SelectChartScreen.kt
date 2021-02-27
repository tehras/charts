package com.github.tehras.charts.ui.selector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.tehras.charts.theme.Margins
import com.github.tehras.charts.ui.ChartScreen
import com.github.tehras.charts.ui.ChartScreen.Bar
import com.github.tehras.charts.ui.ChartScreen.Line
import com.github.tehras.charts.ui.ChartScreen.Pie
import com.github.tehras.charts.ui.ChartScreenStatus

@Composable
fun SelectChartScreen() {
  Scaffold(
    topBar = { TopAppBar(title = { Text(text = "Select Chart") }) }
  ) { SelectChartScreenContent() }
}

@Composable
private fun SelectChartScreenContent() {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
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
    TextButton(
      onClick = { ChartScreenStatus.navigateTo(navigateTo) }
    ) {
      Text(text = text)
    }
  }
}

@Preview
@Composable
fun SelectChartScreenPreview() = SelectChartScreen()
