package com.github.tehras.charts.piechart

import androidx.animation.AnimationBuilder
import androidx.compose.Composable
import androidx.compose.onPreCommit
import androidx.ui.animation.animatedFloat
import androidx.ui.core.Modifier
import androidx.ui.foundation.Canvas
import androidx.ui.graphics.Color
import androidx.ui.graphics.drawscope.drawCanvas
import androidx.ui.layout.fillMaxSize
import androidx.ui.tooling.preview.Preview
import com.github.tehras.charts.piechart.PieChartUtils.calculateAngle
import com.github.tehras.charts.piechart.animation.SimpleChartAnimation
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer
import com.github.tehras.charts.piechart.renderer.SliceDrawer

@Composable
fun PieChart(
    pieChartData: PieChartData,
    modifier: Modifier = Modifier.fillMaxSize(),
    animation: AnimationBuilder<Float> = SimpleChartAnimation,
    sliceDrawer: SliceDrawer = SimpleSliceDrawer()
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
        drawCanvas { canvas, size ->
            var startArc = 0f

            slices.forEach { slice ->
                val arc = calculateAngle(
                    sliceLength = slice.value,
                    totalLength = pieChartData.totalSize,
                    progress = progress
                )

                sliceDrawer.drawSlice(
                    drawScope = this,
                    canvas = canvas,
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
fun PieChartPreview() = PieChartData(
    slices = listOf(
        PieChartData.Slice(25f, Color.Red),
        PieChartData.Slice(42f, Color.Blue),
        PieChartData.Slice(23f, Color.Green)
    )
)