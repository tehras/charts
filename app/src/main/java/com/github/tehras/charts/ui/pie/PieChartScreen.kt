package com.github.tehras.charts.ui.pie

import androidx.compose.Composable
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
        bodyContent = { modifier -> PieChartScreenContent(modifier) }
    )
}

@Composable
fun PieChartScreenContent(modifier: Modifier) {
    val pieChartDataModel = PieChartDataModel()

    Column(
        modifier = modifier.padding(
            horizontal = Margins.horizontal,
            vertical = Margins.vertical
        )
    ) {
        PieChartRow(pieChartDataModel.pieChartData)
        SliceThicknessRow(pieChartDataModel.thickness) {
            pieChartDataModel.thickness = it
        }
        AddOrRemoveSliceRow(pieChartDataModel)
    }
}

@Composable
private fun PieChartRow(pieChartData: PieChartData) {
    Row(modifier = Modifier.fillMaxWidth().height(150.dp)) {
        PieChart(pieChartData = pieChartData)
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
            onValueChange = { onValueUpdated(it) },
            valueRange = 10f.rangeTo(100f)
        )
    }
}

@Composable
private fun AddOrRemoveSliceRow(pieChartDataModel: PieChartDataModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Margins.vertical),
        verticalGravity = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TextButton(
            enabled = pieChartDataModel.slices.size < 9,
            onClick = { pieChartDataModel.addSlice() }
        ) {
            Text("Add slice")
        }
        TextButton(
            enabled = pieChartDataModel.slices.size > 3,
            onClick = { pieChartDataModel.removeSlice() }
        ) {
            Text("Remove slice")
        }
    }
}


@Preview
@Composable
fun PieChartScreenPreview() = PieChartScreen()