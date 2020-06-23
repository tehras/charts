package com.github.tehras.charts.bar

import android.graphics.Paint.Align.RIGHT
import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.unit.Density
import androidx.ui.unit.TextUnit
import androidx.ui.unit.sp
import com.github.tehras.charts.piechart.AxisLine
import com.github.tehras.charts.piechart.utils.toLegacyInt

typealias LabelFormatter = (index: Int, value: Float) -> String

data class BarChartData(
    val bars: List<Bar>,
    val xAxis: XAxis = XAxis(),
    val yAxis: YAxis = YAxis(),
    val valueLabelFormat: LabelFormat = LabelFormat()
) {
    val maxBarValue = calculateMaxBarValue()

    private fun calculateMaxBarValue(): Float {
        var maxValue = 0f
        bars.forEach {
            if (it.value > maxValue) {
                maxValue = it.value
            }
        }

        return maxValue
    }

    data class Bar(
        val value: Float,
        val color: Color,
        val label: String
    ) {
        val paint = Paint().apply {
            this.color = this@Bar.color
            this.isAntiAlias = true
        }
    }

    data class LabelFormat(
        val textColor: Color = Color.White,
        val textSize: TextUnit = 14.sp,
        val drawLocation: DrawLocation = DrawLocation.Inside
    ) {
        private val paint = android.graphics.Paint().apply {
            this.textAlign = android.graphics.Paint.Align.CENTER
            this.color = textColor.toLegacyInt()
        }

        fun paint(density: Density) = paint.apply {
            this.textSize = this@LabelFormat.textSize.value * density.density * density.fontScale
        }

        enum class DrawLocation { Inside, Outside, XAxis }
    }

    data class XAxis(
        val isEnabled: Boolean = true,
        val axisLine: AxisLine = AxisLine()
    )

    data class YAxis(
        val isEnabled: Boolean = true,
        val axisLine: AxisLine = AxisLine(),
        val ratio: Int = 3,
        val textSize: TextUnit = 12.sp,
        val textColor: Color = Color.Black,
        val formatter: LabelFormatter = { _, value -> "%.1f".format(value) }
    ) {
        private val paint = android.graphics.Paint().apply {
            this.textAlign = RIGHT
            this.color = textColor.toLegacyInt()
        }

        fun paint(density: Density) = paint.apply {
            this.textSize = this@YAxis.textSize.value * density.density * density.fontScale
        }
    }
}
