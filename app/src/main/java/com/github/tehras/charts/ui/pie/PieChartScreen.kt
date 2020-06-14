package com.github.tehras.charts.ui.pie

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons.Filled
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.theme.Margins
import com.github.tehras.charts.ui.ChartScreenStatus

@Composable
fun PieChartScreen() {
    Scaffold(
        topAppBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { ChartScreenStatus.navigateHome() }) {
                        Icon(Filled.ArrowBack)
                    }
                },
                title = { Text(text = "Pie Chart") }
            )
        },
        bodyContent = { modifier ->
            PieChartScreenContent(
                modifier
            )
        }
    )
}

@Composable
fun PieChartScreenContent(modifier: Modifier) {
    var sliceThickness by mutableStateOf(10f)

    Column(
        modifier = modifier.padding(
            horizontal = Margins.horizontal,
            vertical = Margins.vertical
        )
    ) {
        PieChartRow(sliceThickness)
        SliceThicknessRow(sliceThickness) {
            sliceThickness = it
        }
        AddOrRemoveSliceRow()
    }
}

@Composable
private fun PieChartRow(sliceThickness: Float) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(150.dp)
    ) {
        PieChart(
            pieChartData = PieChartData(
                slices = SliceGenerator.slices,
                sliceThickness = sliceThickness
            )
        )
    }
}

@Composable
private fun SliceThicknessRow(sliceThickness: Float, onValueUpdated: (Float) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Margins.verticalLarge),
        verticalGravity = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.gravity(Alignment.CenterVertically)
                .padding(end = Margins.horizontal),
            text = "Slice thickness"
        )
        Slider(
            value = sliceThickness,
            onValueChange = {
                onValueUpdated(it)
            },
            valueRange = 10f.rangeTo(100f)
        )
    }
}

@Composable
private fun AddOrRemoveSliceRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Margins.vertical),
        verticalGravity = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TextButton(
            enabled = SliceGenerator.slices.size < 9,
            onClick = { SliceGenerator.addSlice() }
        ) {
            Text("Add slice")
        }
        TextButton(
            enabled = SliceGenerator.slices.size > 3,
            onClick = { SliceGenerator.removeSlice() }
        ) {
            Text("Remove slice")
        }
    }
}


@Preview
@Composable
fun PieChartScreenPreview() = PieChartScreen()