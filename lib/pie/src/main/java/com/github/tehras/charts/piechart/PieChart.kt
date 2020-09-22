package com.github.tehras.charts.piechart

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawCanvas
import com.github.tehras.charts.piechart.PieChartUtils.calculateAngle
import com.github.tehras.charts.piechart.animation.SimpleChartAnimation
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer
import com.github.tehras.charts.piechart.renderer.SliceDrawer


@Composable
fun PieChart(
    pieChartData: PieChartData,
    modifier: Modifier = Modifier.fillMaxSize(),
    animation: AnimationSpec<Float> = SimpleChartAnimation(),
    sliceDrawer: SliceDrawer = SimpleSliceDrawer()
) {
    val transitionProgress = animatedFloat(initVal = 0f)

    // When slices value changes we want to re-animated the chart.
    onCommit(pieChartData.slices, {
        transitionProgress.snapTo(0f)
        transitionProgress.animateTo(1f, anim = animation)
    })

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


@Composable
fun PieChartPreview() = PieChartData(
    slices = listOf(
        PieChartData.Slice(25f, Color.Red),
        PieChartData.Slice(42f, Color.Blue),
        PieChartData.Slice(23f, Color.Green)
    )
)