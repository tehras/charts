package com.github.tehras.charts.ui.selector

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Row
import androidx.ui.layout.RowScope.gravity
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
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
        bodyContent = { modifier ->
            SelectChartScreenContent(
                modifier
            )
        }
    )
}

@Composable
private fun SelectChartScreenContent(modifier: Modifier) {
    VerticalScroller(modifier = modifier.fillMaxSize()) {
        PieChartRow()
    }
}

@Composable
private fun PieChartRow() {
    Row(
        modifier = Modifier
            .padding(horizontal = Margins.horizontal, vertical = Margins.vertical)
            .gravity(Alignment.CenterVertically),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                ChartScreenStatus.navigateTo(
                    ChartScreen.Pie
                )
            },
            text = { Text(text = "Pie") }
        )
    }
}

@Preview
@Composable
fun SelectChartScreenPreview() {
    SelectChartScreen()
}
