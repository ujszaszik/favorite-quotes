package com.newstore.compose.animation

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

private const val SCALE_AND_ALPHA_TRANSITION_LABEL = "ScaleAndAlphaTransition"
private const val ALPHA_TRANSITION_LABEL = "AlphaTransition"
private const val SCALE_TRANSITION_LABEL = "ScaleATransition"

private enum class State { PLACING, PLACED }

data class ScaleAndAlphaArgs(
    val fromScale: Float,
    val toScale: Float,
    val fromAlpha: Float,
    val toAlpha: Float
)

@Composable
fun scaleAndAlpha(
    args: ScaleAndAlphaArgs,
    animation: FiniteAnimationSpec<Float>
): Pair<Float, Float> {

    val transitionState =
        remember { MutableTransitionState(State.PLACING).apply { targetState = State.PLACED } }
    val transition = updateTransition(transitionState, label = SCALE_AND_ALPHA_TRANSITION_LABEL)

    val alpha by transition.animateFloat(
        transitionSpec = { animation },
        label = ALPHA_TRANSITION_LABEL
    ) { state ->
        when (state) {
            State.PLACING -> args.fromAlpha
            State.PLACED -> args.toAlpha
        }
    }

    val scale by transition.animateFloat(
        transitionSpec = { animation },
        label = SCALE_TRANSITION_LABEL
    ) { state ->
        when (state) {
            State.PLACING -> args.fromScale
            State.PLACED -> args.toScale
        }
    }
    return alpha to scale
}