package com.github.tehras.charts.ui.bar

import androidx.compose.Composable
import androidx.ui.core.Alignment.Companion.CenterHorizontally
import androidx.ui.core.Alignment.Companion.CenterVertically
import androidx.ui.core.Modifier
import androidx.ui.foundation.Border
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.SolidColor
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.gravity
import androidx.ui.material.*
import androidx.ui.material.icons.Icons.Filled
import androidx.ui.material.icons.filled.Add
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.material.icons.filled.Remove
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.BarChartData.LabelFormat.DrawLocation
import com.github.tehras.charts.theme.Margins
import com.github.tehras.charts.ui.ChartScreenStatus

@Composable
fun BarChartScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { ChartScreenStatus.navigateHome() }) {
                        Icon(Filled.ArrowBack)
                    }
                },
                title = { Text(text = "Bar Chart") }
            )
        },
        bodyContent = { BarChartScreenContent() }
    )
}

@Composable
private fun BarChartScreenContent() {
    val barChartDataModel = BarChartDataModel()

    Column(
        modifier = Modifier.padding(
            horizontal = Margins.horizontal,
            vertical = Margins.vertical
        )
    ) {
        BarChartRow(barChartDataModel.barChartData)
        DrawValueLocation(barChartDataModel) {
            barChartDataModel.valueLocation = it
        }
        AddOrRemoveBar(barChartDataModel)
    }
}

@Composable
fun DrawValueLocation(
    barChartDataModel: BarChartDataModel,
    newLocation: (DrawLocation) -> Unit
) {
    val selectedAlignment = barChartDataModel.valueLocation

    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(top = Margins.verticalLarge),
        verticalGravity = CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = Margins.horizontal, vertical = Margins.vertical)
                .gravity(CenterHorizontally)
        ) {
            DrawLocation.values().forEach { location ->
                val color = if (selectedAlignment == location) {
                    Color.Black
                } else {
                    Color.Transparent
                }

                TextButton(
                    border = Border(2.dp, SolidColor(color)),
                    onClick = { newLocation(location) }
                ) {
                    Text(location.name)
                }
            }
        }
    }
}

@Composable
fun AddOrRemoveBar(barChartDataModel: BarChartDataModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Margins.vertical),
        verticalGravity = CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            enabled = barChartDataModel.bars.size > 2,
            onClick = { barChartDataModel.removeBar() },
            shape = CircleShape
        ) { Icon(Filled.Remove) }
        Row(
            modifier = Modifier.padding(horizontal = Margins.horizontal),
            verticalGravity = CenterVertically
        ) {
            Text(text = "Bars: ")
            Text(
                text = barChartDataModel.bars.count().toString(),
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
            )
        }
        Button(
            enabled = barChartDataModel.bars.size < 6,
            onClick = { barChartDataModel.addBar() },
            shape = CircleShape
        ) { Icon(Filled.Add) }
    }
}

@Composable
private fun BarChartRow(barChartData: BarChartData) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(250.dp)
            .padding(vertical = Margins.verticalLarge)
    ) {
        BarChart(barChartData = barChartData)
    }
}
