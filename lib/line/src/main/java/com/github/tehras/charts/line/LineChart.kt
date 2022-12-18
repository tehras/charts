package com.github.tehras.charts.line

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.github.tehras.charts.line.LineChartUtils.calculateDrawableArea
import com.github.tehras.charts.line.LineChartUtils.calculateFillPath
import com.github.tehras.charts.line.LineChartUtils.calculateLinePath
import com.github.tehras.charts.line.LineChartUtils.calculatePointLocation
import com.github.tehras.charts.line.LineChartUtils.calculateXAxisDrawableArea
import com.github.tehras.charts.line.LineChartUtils.calculateXAxisLabelsDrawableArea
import com.github.tehras.charts.line.LineChartUtils.calculateYAxisDrawableArea
import com.github.tehras.charts.line.LineChartUtils.withProgress
import com.github.tehras.charts.line.renderer.line.LineDrawer
import com.github.tehras.charts.line.renderer.line.LineShader
import com.github.tehras.charts.line.renderer.line.NoLineShader
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.line.renderer.point.PointDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.xaxis.XAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.YAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation

@Composable
fun LineChart(
  modifier: Modifier = Modifier,
  linesChartData: List<LineChartData>,
  labels: List<String> = linesChartData.maxByOrNull { it.points.size }?.points?.map { it.label }
    ?: emptyList(),
  animation: AnimationSpec<Float> = simpleChartAnimation(),
  pointDrawer: PointDrawer = FilledCircularPointDrawer(),
  lineDrawer: LineDrawer = SolidLineDrawer(),
  lineShader: LineShader = NoLineShader,
  xAxisDrawer: XAxisDrawer = SimpleXAxisDrawer(),
  yAxisDrawer: YAxisDrawer = SimpleYAxisDrawer(),
  horizontalOffset: Float = 5f
) {
  check(horizontalOffset in 0f..25f) {
    "Horizontal offset is the % offset from sides, " +
            "and should be between 0%-25%"
  }

  val transitionAnimation = remember(linesChartData.forEach { it.points }) {
    mutableStateListOf(
      *linesChartData.map { Animatable(0f) }.toTypedArray()
    )
  }

  LaunchedEffect(linesChartData.forEach { it.points }) {
    repeat(linesChartData.size) { index ->
      transitionAnimation[index].snapTo(0f)
      transitionAnimation[index].animateTo(
        targetValue = 1f,
        animationSpec = animation
      )
    }
  }

  Canvas(modifier = modifier.fillMaxSize()) {
    drawIntoCanvas { canvas ->

      val yAxisDrawableArea = calculateYAxisDrawableArea(
        xAxisLabelSize = xAxisDrawer.requiredHeight(this),
        size = size
      )
      val xAxisDrawableArea = calculateXAxisDrawableArea(
        yAxisWidth = yAxisDrawableArea.width,
        labelHeight = xAxisDrawer.requiredHeight(this),
        size = size
      )
      val xAxisLabelsDrawableArea = calculateXAxisLabelsDrawableArea(
        xAxisDrawableArea = xAxisDrawableArea,
        offset = horizontalOffset
      )
      val chartDrawableArea = calculateDrawableArea(
        xAxisDrawableArea = xAxisDrawableArea,
        yAxisDrawableArea = yAxisDrawableArea,
        size = size,
        offset = horizontalOffset
      )

      // Draw the X Axis line.
      xAxisDrawer.drawAxisLine(
        drawScope = this,
        drawableArea = xAxisDrawableArea,
        canvas = canvas
      )

      xAxisDrawer.drawAxisLabels(
        drawScope = this,
        canvas = canvas,
        drawableArea = xAxisLabelsDrawableArea,
        labels = labels
      )

      // Draw the Y Axis line.
      yAxisDrawer.drawAxisLine(
        drawScope = this,
        canvas = canvas,
        drawableArea = yAxisDrawableArea
      )

      yAxisDrawer.drawAxisLabels(
        drawScope = this,
        canvas = canvas,
        drawableArea = yAxisDrawableArea,
        minValue = linesChartData.minOf { it.maxYValue },
        maxValue = linesChartData.maxOf { it.maxYValue }
      )

      linesChartData.forEachIndexed { index, lineChartData ->
        drawLine(
          canvas = canvas,
          lineChartData = lineChartData,
          transitionAnimation = transitionAnimation[index],
          pointDrawer = pointDrawer,
          lineDrawer = lineDrawer,
          lineShader = lineShader,
          chartDrawableArea = chartDrawableArea
        )
      }
    }
  }
}

private fun DrawScope.drawLine(
  pointDrawer: PointDrawer = FilledCircularPointDrawer(),
  lineDrawer: LineDrawer = SolidLineDrawer(),
  lineShader: LineShader = NoLineShader,
  canvas: Canvas,
  transitionAnimation: Animatable<Float, AnimationVector1D>,
  lineChartData: LineChartData,
  chartDrawableArea: Rect
) {

  // Draw the chart line.
  lineDrawer.drawLine(
    drawScope = this,
    canvas = canvas,
    linePath = calculateLinePath(
      drawableArea = chartDrawableArea,
      lineChartData = lineChartData,
      transitionProgress = transitionAnimation.value
    )
  )

  lineShader.fillLine(
    drawScope = this,
    canvas = canvas,
    fillPath = calculateFillPath(
      drawableArea = chartDrawableArea,
      lineChartData = lineChartData,
      transitionProgress = transitionAnimation.value
    )
  )

  lineChartData.points.forEachIndexed { index, point ->
    withProgress(
      index = index,
      lineChartData = lineChartData,
      transitionProgress = transitionAnimation.value
    ) {
      pointDrawer.drawPoint(
        drawScope = this,
        canvas = canvas,
        center = calculatePointLocation(
          drawableArea = chartDrawableArea,
          lineChartData = lineChartData,
          point = point,
          index = index
        )
      )
    }
  }
}

