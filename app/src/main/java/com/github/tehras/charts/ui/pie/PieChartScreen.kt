package com.github.tehras.charts.ui.pie

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons.Filled
import androidx.ui.material.icons.filled.Add
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.material.icons.filled.Remove
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.theme.Margins
import com.github.tehras.charts.ui.ChartScreenStatus

@Composable
fun PieChartScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { ChartScreenStatus.navigateHome() }) {
                        Icon(Filled.ArrowBack)
                    }
                },
                title = { Text(text = "Pie Chart") }
            )
        },
        bodyContent = { PieChartScreenContent() }
    )
}

@Composable
private fun PieChartScreenContent() {
    val pieChartDataModel = PieChartDataModel()

    Column(
        modifier = Modifier.padding(
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
    Row(modifier = Modifier.fillMaxWidth().height(150.dp).padding(vertical = Margins.vertical)) {
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
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            enabled = pieChartDataModel.slices.size > 3,
            onClick = { pieChartDataModel.removeSlice() },
            shape = CircleShape
        ) {
            Icon(Filled.Remove)
        }
        Row(
            modifier = Modifier.padding(horizontal = Margins.horizontal),
            verticalGravity = Alignment.CenterVertically
        ) {
            Text(text = "Slices: ")
            Text(
                text = pieChartDataModel.slices.count().toString(),
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
            )
        }
        Button(
            enabled = pieChartDataModel.slices.size < 9,
            onClick = { pieChartDataModel.addSlice() },
            shape = CircleShape
        ) {
            Icon(Filled.Add)
        }
    }
}


@Preview
@Composable
fun PieChartScreenPreview() = PieChartScreen()