package com.github.tehras.charts.bar

import androidx.animation.AnimationBuilder
import androidx.animation.TweenBuilder
import androidx.compose.Composable
import androidx.compose.onPreCommit
import androidx.ui.animation.animatedFloat
import androidx.ui.core.Modifier
import androidx.ui.core.drawBehind
import androidx.ui.foundation.Canvas
import androidx.ui.geometry.Offset
import androidx.ui.geometry.Rect
import androidx.ui.graphics.Canvas
import androidx.ui.graphics.drawscope.DrawScope
import androidx.ui.graphics.drawscope.drawCanvas
import androidx.ui.layout.fillMaxSize
import androidx.ui.res.integerResource
import com.github.tehras.charts.bar.BarChartData.LabelFormat.DrawLocation.*
import com.github.tehras.charts.bar.BarChartUtils.axisAreas
import com.github.tehras.charts.bar.BarChartUtils.barDrawableArea
import com.github.tehras.charts.bar.BarChartUtils.forEachWithArea

@Composable
fun BarChart(
    barChartData: BarChartData,
    modifier: Modifier = Modifier.fillMaxSize(),
    animation: AnimationBuilder<Float> = DefaultBarChartAnimation
) {
    val transitionProgress = animatedFloat(initVal = 0f)
    val resetAnimation = {
        transitionProgress.snapTo(0f)
        transitionProgress.animateTo(1f, anim = animation)
    }

    onPreCommit(barChartData.bars) { resetAnimation() }
    onPreCommit(barChartData.valueLabelFormat) { resetAnimation() }

    val progress = transitionProgress.value

    Canvas(modifier = modifier
        .drawBehind {
            drawCanvas { canvas, size ->
                val (xAxisArea, yAxisArea) = axisAreas(
                    density = this,
                    barChartData = barChartData,
                    totalSize = size
                )
                val barDrawableArea = barDrawableArea(xAxisArea)

                drawYAxis(canvas, yAxisArea, barChartData)
                drawXAxis(canvas, xAxisArea, barChartData)
                drawBars(canvas, barDrawableArea, barChartData, progress)
            }
        }
    ) {
        /**
         *  Typically we could draw everything here, but because of the lack of canvas.drawText
         *  APIs we have to use Android's `nativeCanvas` which seems to be drawn behind
         *  Compose's canvas.
         */
        drawCanvas { canvas, size ->
            val (xAxisArea, yAxisArea) = axisAreas(
                barChartData = barChartData,
                totalSize = size,
                density = this
            )
            val barDrawableArea = barDrawableArea(xAxisArea)

            drawBarLabels(canvas, barDrawableArea, barChartData, progress)
            drawValueLabels(canvas, yAxisArea, barChartData)
        }
    }
}

fun DrawScope.drawYAxis(
    canvas: Canvas,
    yAxisArea: Rect,
    barChartData: BarChartData
) {
    val yAxis = barChartData.yAxis
    if (!yAxis.isEnabled) return

    val xAxisLineThickness = barChartData.xAxis.axisLine.thickness.value

    val yAxisLine = yAxis.axisLine
    // Draw line.
    val dx = yAxisArea.right - (yAxisLine.thickness.toPx().value / 2)

    canvas.drawLine(
        p1 = Offset(dy = yAxisArea.top, dx = dx),
        p2 = Offset(dy = yAxisArea.bottom, dx = dx + xAxisLineThickness),
        paint = yAxisLine.paint(this)
    )
}

fun DrawScope.drawXAxis(
    canvas: Canvas,
    xAxisArea: Rect,
    barChartData: BarChartData
) {
    val xAxis = barChartData.xAxis
    if (!xAxis.isEnabled) return

    val yAxisLineThickness = barChartData.yAxis.axisLine.thickness.value

    val axisLine = xAxis.axisLine
    // Draw line.
    val dy = xAxisArea.top + (axisLine.thickness.toPx().value / 2)

    canvas.drawLine(
        p1 = Offset(dy = dy, dx = xAxisArea.left - yAxisLineThickness),
        p2 = Offset(dy = dy, dx = xAxisArea.right),
        paint = axisLine.paint(this)
    )
}

fun drawBars(
    canvas: Canvas,
    barDrawableArea: Rect,
    barChartData: BarChartData,
    progress: Float
) {
    barChartData.forEachWithArea(barDrawableArea, progress) { barArea, bar ->
        canvas.drawRect(barArea, bar.paint)
    }
}

fun DrawScope.drawBarLabels(
    canvas: Canvas,
    barDrawableArea: Rect,
    barChartData: BarChartData,
    progress: Float
) {
    barChartData.forEachWithArea(barDrawableArea, progress) { barArea, bar ->
        val xCenter = barArea.left + (barArea.width / 2)
        val labelFormat = barChartData.valueLabelFormat
        val yCenter = when (labelFormat.drawLocation) {
            Inside -> (barArea.top + barArea.bottom) / 2
            Outside -> (barArea.top) - (labelFormat.textSize.toPx().value) / 2
            XAxis -> barArea.bottom + (labelFormat.textSize.toPx().value) * (3f / 2f)
        }

        canvas.nativeCanvas.drawText(bar.label, xCenter, yCenter, labelFormat.paint(this))
    }
}

fun DrawScope.drawValueLabels(
    canvas: Canvas,
    yAxisArea: Rect,
    barChartData: BarChartData
) {
    val yAxis = barChartData.yAxis
    val textHeight = yAxis.textSize.toPx().value
    val topOffset = (yAxisArea.height * (barChartData.topOffset / 100f))
    val top = yAxisArea.top + topOffset
    val maxHeight = yAxisArea.height - topOffset
    val labelCount = ((maxHeight / textHeight) / yAxis.ratio).toInt()
    val spaceBetween = maxHeight / labelCount

    for (i in 0..labelCount) {
        val formattedValue =
            yAxis.formatter(i, (barChartData.maxBarValue * (i.toFloat() / labelCount.toFloat())))
        val yCenter = top + ((labelCount - i) * spaceBetween)
        val yRight = 3 * (yAxisArea.right - yAxisArea.left) / 4

        canvas.nativeCanvas.drawText(formattedValue, yRight, yCenter, yAxis.paint(this))
    }
}

@Composable
private val DefaultBarChartAnimation: AnimationBuilder<Float>
    get() = TweenBuilder<Float>().apply {
        duration = +integerResource(id = android.R.integer.config_mediumAnimTime)
    }
