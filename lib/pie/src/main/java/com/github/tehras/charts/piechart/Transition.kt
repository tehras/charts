package com.github.tehras.charts.piechart

import androidx.animation.FastOutSlowInEasing
import androidx.animation.FloatPropKey
import androidx.animation.transitionDefinition
import androidx.compose.Composable
import androidx.ui.animation.Transition

private val progressKey = FloatPropKey()

private enum class ChartState {
    START, END
}

private val definition = transitionDefinition {
    state(ChartState.START) {
        this[progressKey] = 0f
    }

    state(ChartState.END) {
        this[progressKey] = 1f
    }

    transition {
        progressKey using tween {
            duration = 750
            delay = 250
            easing = FastOutSlowInEasing
        }
    }
}

@Composable
fun AnimatedChart(
    child: @Composable() (Float) -> Unit
) {
    Transition(
        definition = definition,
        initState = ChartState.START,
        toState = ChartState.END
    ) {
        val progress = it[progressKey]

        child(progress)
    }
}