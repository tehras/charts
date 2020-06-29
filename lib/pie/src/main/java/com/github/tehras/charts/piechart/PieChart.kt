package com.github.tehras.charts.piechart

import androidx.animation.AnimationBuilder
import androidx.animation.TweenBuilder
import androidx.compose.Composable
import androidx.compose.onPreCommit
import androidx.ui.animation.animatedFloat
import androidx.ui.core.Modifier
import androidx.ui.foundation.Canvas
import androidx.ui.graphics.Color
import androidx.ui.graphics.drawscope.drawCanvas
import androidx.ui.layout.fillMaxSize
import androidx.ui.res.integerResource
import androidx.ui.tooling.preview.Preview
import com.github.tehras.charts.piechart.PieChartUtils.calculateAngle
import com.github.tehras.charts.piechart.PieChartUtils.calculateDrawableArea
import com.github.tehras.charts.piechart.PieChartUtils.calculateSectorThickness
import com.github.tehras.charts.piechart.animation.SimpleChartAnimation

@Composable
fun PieChart(
    pieChartData: PieChartData,
    modifier: Modifier = Modifier.fillMaxSize(),
    animation: AnimationBuilder<Float> = SimpleChartAnimation
) {
    val transitionProgress = animatedFloat(initVal = 0f)

    // When slices value changes we want to re-animated the chart.
    onPreCommit(pieChartData.slices) {
        transitionProgress.snapTo(0f)
        transitionProgress.animateTo(1f, anim = animation)
    }

    DrawChart(
        pieChartData = pieChartData,
        modifier = modifier,
        progress = transitionProgress.value
    )
}

@Composable
private fun DrawChart(
    pieChartData: PieChartData,
    modifier: Modifier,
    progress: Float
) {
    val slices = pieChartData.slices

    Canvas(modifier = modifier) {
        drawCanvas { canvas, size ->
            val rect = calculateDrawableArea(size, pieChartData)
            var startArc = 0f

            slices.forEach { slice ->
                val arc =
                    calculateAngle(
                        sliceLength = slice.value,
                        totalLength = pieChartData.totalSize,
                        progress = progress
                    )

                canvas.drawArc(
                    rect = rect,
                    paint = pieChartData.sectionPaint.apply {
                        color = slice.color
                        strokeWidth = calculateSectorThickness(
                            size = size,
                            pieChartData = pieChartData
                        )
                    },
                    startAngle = startArc,
                    sweepAngle = arc,
                    useCenter = false
                )

                startArc += arc
            }
        }
    }
}

@Composable
private val DefaultPieChartAnimation: AnimationBuilder<Float>
    get() = TweenBuilder<Float>().apply {
        duration = +integerResource(id = android.R.integer.config_mediumAnimTime)
    }

@Preview
@Composable
fun PieChartPreview() = PieChartData(
    slices = listOf(
        PieChartData.Slice(25f, Color.Red),
        PieChartData.Slice(42f, Color.Blue),
        PieChartData.Slice(23f, Color.Green)
    )
)