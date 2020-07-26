package com.github.tehras.charts.bar

import androidx.animation.AnimationSpec
import androidx.compose.Composable
import androidx.compose.onPreCommit
import androidx.ui.animation.animatedFloat
import androidx.ui.core.Modifier
import androidx.ui.core.drawBehind
import androidx.ui.foundation.Canvas
import androidx.ui.graphics.drawscope.drawCanvas
import androidx.ui.layout.fillMaxSize
import com.github.tehras.charts.bar.BarChartUtils.axisAreas
import com.github.tehras.charts.bar.BarChartUtils.barDrawableArea
import com.github.tehras.charts.bar.BarChartUtils.forEachWithArea
import com.github.tehras.charts.bar.renderer.bar.BarDrawer
import com.github.tehras.charts.bar.renderer.bar.SimpleBarDrawer
import com.github.tehras.charts.bar.renderer.label.LabelDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.bar.renderer.xaxis.XAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.YAxisDrawer
import com.github.tehras.charts.piechart.animation.SimpleChartAnimation

@Composable
fun BarChart(
    barChartData: BarChartData,
    modifier: Modifier = Modifier.fillMaxSize(),
    animation: AnimationSpec<Float> = SimpleChartAnimation,
    barDrawer: BarDrawer = SimpleBarDrawer(),
    xAxisDrawer: XAxisDrawer = SimpleXAxisDrawer(),
    yAxisDrawer: YAxisDrawer = SimpleYAxisDrawer(),
    labelDrawer: LabelDrawer = SimpleValueDrawer()
) {
    val transitionProgress = animatedFloat(initVal = 0f)

    onPreCommit(barChartData.bars) {
        transitionProgress.snapTo(0f)
        transitionProgress.animateTo(1f, anim = animation)
    }

    val progress = transitionProgress.value

    Canvas(modifier = modifier
        .drawBehind {
            drawCanvas { canvas, size ->
                val (xAxisArea, yAxisArea) = axisAreas(
                    drawScope = this,
                    totalSize = size,
                    xAxisDrawer = xAxisDrawer,
                    labelDrawer = labelDrawer
                )
                val barDrawableArea = barDrawableArea(xAxisArea)

                // Draw yAxis line.
                yAxisDrawer.drawAxisLine(
                    drawScope = this,
                    canvas = canvas,
                    drawableArea = yAxisArea
                )

                // Draw xAxis line.
                xAxisDrawer.drawAxisLine(
                    drawScope = this,
                    canvas = canvas,
                    drawableArea = xAxisArea
                )
                // Draw each bar.
                barChartData.forEachWithArea(
                    this,
                    barDrawableArea,
                    progress,
                    labelDrawer
                ) { barArea, bar ->
                    barDrawer.drawBar(
                        drawScope = this,
                        canvas = canvas,
                        barArea = barArea,
                        bar = bar
                    )
                }
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
                drawScope = this,
                totalSize = size,
                xAxisDrawer = xAxisDrawer,
                labelDrawer = labelDrawer
            )
            val barDrawableArea = barDrawableArea(xAxisArea)

            barChartData.forEachWithArea(
                this,
                barDrawableArea,
                progress,
                labelDrawer
            ) { barArea, bar ->
                labelDrawer.drawLabel(
                    drawScope = this,
                    canvas = canvas,
                    label = bar.label,
                    barArea = barArea,
                    xAxisArea = xAxisArea
                )
            }

            yAxisDrawer.drawAxisLabels(
                drawScope = this,
                canvas = canvas,
                minValue = barChartData.minYValue,
                maxValue = barChartData.maxYValue,
                drawableArea = yAxisArea
            )
        }
    }
}