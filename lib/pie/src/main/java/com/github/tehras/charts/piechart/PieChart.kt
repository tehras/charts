package com.github.tehras.charts.piechart

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import com.github.tehras.charts.piechart.PieChartUtils.calculateAngle
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer
import com.github.tehras.charts.piechart.renderer.SliceDrawer

@Composable
fun PieChart(
  pieChartData: PieChartData,
  modifier: Modifier = Modifier,
  animation: AnimationSpec<Float> = simpleChartAnimation(),
  sliceDrawer: SliceDrawer = SimpleSliceDrawer()
) {
  val transitionProgress = remember(pieChartData.slices) { Animatable(initialValue = 0f) }

  // When slices value changes we want to re-animated the chart.
  LaunchedEffect(pieChartData.slices) {
    transitionProgress.animateTo(1f, animationSpec = animation)
  }

  DrawChart(
    pieChartData = pieChartData,
    modifier = modifier.fillMaxSize(),
    progress = transitionProgress.value,
    sliceDrawer = sliceDrawer
  )
}

@Composable
private fun DrawChart(
  pieChartData: PieChartData,
  modifier: Modifier,
  progress: Float,
  sliceDrawer: SliceDrawer
) {
  val slices = pieChartData.slices

  Canvas(modifier = modifier) {
    drawIntoCanvas {
      var startArc = 0f

      slices.forEach { slice ->
        val arc = calculateAngle(
          sliceLength = slice.value,
          totalLength = pieChartData.totalSize,
          progress = progress
        )

        sliceDrawer.drawSlice(
          drawScope = this,
          canvas = drawContext.canvas,
          area = size,
          startAngle = startArc,
          sweepAngle = arc,
          slice = slice
        )

        startArc += arc
      }
    }
  }
}

@Preview
@Composable
fun PieChartPreview() = PieChart(
  pieChartData = PieChartData(
    slices = listOf(
      PieChartData.Slice(25f, Color.Red),
      PieChartData.Slice(42f, Color.Blue),
      PieChartData.Slice(23f, Color.Green)
    )
  )
)