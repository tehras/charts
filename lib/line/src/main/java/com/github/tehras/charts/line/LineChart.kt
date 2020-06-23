package com.github.tehras.charts.line

import androidx.animation.AnimationBuilder
import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.ui.animation.animatedFloat
import androidx.ui.core.Modifier
import androidx.ui.foundation.Canvas
import androidx.ui.graphics.drawscope.drawCanvas
import androidx.ui.layout.fillMaxSize
import com.github.tehras.charts.line.LineChartUtils.calculateDrawableArea
import com.github.tehras.charts.line.LineChartUtils.calculateLinePath
import com.github.tehras.charts.line.LineChartUtils.calculatePointLocation
import com.github.tehras.charts.line.LineChartUtils.calculateXAxisDrawableArea
import com.github.tehras.charts.line.LineChartUtils.calculateXAxisLabelsDrawableArea
import com.github.tehras.charts.line.LineChartUtils.calculateYAxisDrawableArea
import com.github.tehras.charts.line.line.LineDrawer
import com.github.tehras.charts.line.line.SolidLineDrawer
import com.github.tehras.charts.line.point.FilledPointDrawer
import com.github.tehras.charts.line.point.PointDrawer
import com.github.tehras.charts.line.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.xaxis.XAxisDrawer
import com.github.tehras.charts.line.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.line.yaxis.YAxisDrawer
import com.github.tehras.charts.piechart.animation.SimpleChartAnimation

@Composable
fun LineChart(
    lineChartData: LineChartData,
    modifier: Modifier = Modifier.fillMaxSize(),
    animation: AnimationBuilder<Float> = SimpleChartAnimation,
    pointDrawer: PointDrawer = FilledPointDrawer(),
    lineDrawer: LineDrawer = SolidLineDrawer(),
    xAxisDrawer: XAxisDrawer = SimpleXAxisDrawer(),
    yAxisDrawer: YAxisDrawer = SimpleYAxisDrawer(),
    horizontalOffset: Float = 5f
) {
    check(horizontalOffset in 0f..50f) {
        "Horizontal offset is the % offset from sides, " +
                "and should be between 10%-100%"
    }

    val transitionProgress = animatedFloat(initVal = 0f)
    onCommit(lineChartData.points) {
        transitionProgress.snapTo(0f)
        transitionProgress.animateTo(1f, animation)
    }

    Canvas(modifier = modifier) {
        drawCanvas { canvas, size ->
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

            // Draw the chart line.
            lineDrawer.drawLine(
                drawScope = this,
                canvas = canvas,
                linePath = calculateLinePath(
                    drawableArea = chartDrawableArea,
                    lineChartData = lineChartData
                )
            )

            lineChartData.points.forEachIndexed { index, point ->
                pointDrawer.drawPoint(
                    drawScope = this,
                    canvas = canvas,
                    offset = calculatePointLocation(
                        drawableArea = chartDrawableArea,
                        lineChartData = lineChartData,
                        point = point,
                        index = index
                    )
                )
            }

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
                labels = lineChartData.points.map { it.label }
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
                minValue = lineChartData.minYValue,
                maxValue = lineChartData.maxYValue,
                padBy = lineChartData.padBy
            )
        }
    }
}
