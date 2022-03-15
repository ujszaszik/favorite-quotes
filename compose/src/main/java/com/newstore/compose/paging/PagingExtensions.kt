package com.newstore.compose.paging

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.let {
        it.index >= layoutInfo.totalItemsCount - 1
    } ?: false