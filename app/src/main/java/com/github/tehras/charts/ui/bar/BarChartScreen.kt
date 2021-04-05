package com.github.tehras.charts.ui.bar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer.DrawLocation
import com.github.tehras.charts.theme.Margins
import com.github.tehras.charts.ui.ChartScreenStatus

@Composable
fun BarChartScreen() {
  Scaffold(
    topBar = {
      TopAppBar(
        navigationIcon = {
          IconButton(onClick = { ChartScreenStatus.navigateHome() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Go back to home")
          }
        },
        title = { Text(text = "Bar Chart") }
      )
    },
  ) { BarChartScreenContent() }
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
    BarChartRow(barChartDataModel)
    DrawValueLocation(barChartDataModel) {
      barChartDataModel.labelLocation = it
    }
    AddOrRemoveBar(barChartDataModel)
  }
}

@Composable
fun DrawValueLocation(
  barChartDataModel: BarChartDataModel,
  newLocation: (DrawLocation) -> Unit
) {
  val selectedAlignment = remember(barChartDataModel.labelDrawer) {
    barChartDataModel.labelLocation
  }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = Margins.verticalLarge),
    verticalAlignment = CenterVertically
  ) {
    Row(
      horizontalArrangement = Arrangement.SpaceEvenly,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = Margins.horizontal, vertical = Margins.vertical)
        .align(CenterVertically)
    ) {
      DrawLocation.values().forEach { location ->
        OutlinedButton(
          border = ButtonDefaults.outlinedBorder.takeIf { selectedAlignment == location },
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
    verticalAlignment = CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    Button(
      enabled = barChartDataModel.bars.size > 2,
      onClick = { barChartDataModel.removeBar() },
      shape = CircleShape
    ) { Icon(Icons.Filled.Remove, contentDescription = "Remove bar from chart") }
    Row(
      modifier = Modifier.padding(horizontal = Margins.horizontal),
      verticalAlignment = CenterVertically
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
    ) { Icon(Icons.Filled.Add, contentDescription = "Add bar to chart") }
  }
}

@Composable
private fun BarChartRow(barChartDataModel: BarChartDataModel) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(250.dp)
      .padding(vertical = Margins.verticalLarge)
  ) {
    BarChart(
      barChartData = barChartDataModel.barChartData,
      labelDrawer = barChartDataModel.labelDrawer
    )
  }
}

@Preview
@Composable
fun BarChartPreview() = BarChartScreen()