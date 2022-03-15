package com.newstore.compose.animation

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun <T> AnimatedListItem(
    state: LazyListState,
    index: Int,
    item: T,
    itemContent: @Composable (T, Modifier) -> Unit
) {
    val (delay, easing) = state.calculateDelayAndEasing(index)
    val animation =
        tween<Float>(durationMillis = 500, delayMillis = delay, easing = easing)
    val args =
        ScaleAndAlphaArgs(fromScale = 2f, toScale = 1f, fromAlpha = 0f, toAlpha = 1f)
    val (scale, alpha) = scaleAndAlpha(args = args, animation = animation)

    val itemModifier =
        Modifier.graphicsLayer(alpha = alpha, scaleY = scale)

    itemContent.invoke(item, itemModifier)
}

@Composable
fun LazyListState.calculateDelayAndEasing(index: Int): Pair<Int, Easing> {
    val firstVisibleRow = firstVisibleItemIndex
    val visibleRows = layoutInfo.visibleItemsInfo.count()
    val scrollingToBottom = firstVisibleRow < index
    val isFirstLoad = visibleRows == 0
    val rowDelay = 50 * when {
        isFirstLoad -> index
        scrollingToBottom -> visibleRows + firstVisibleRow - index
        else -> 1
    }
    val easing =
        if (scrollingToBottom || isFirstLoad) LinearOutSlowInEasing else FastOutSlowInEasing
    return rowDelay to easing
}