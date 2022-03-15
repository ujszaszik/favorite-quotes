package com.newstore.compose.paging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.newstore.compose.animation.AnimatedListItem

@Composable
fun <T> PagingColumn(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemContent: @Composable (T, Modifier) -> Unit,
    emptyContent: @Composable () -> Unit,
    loadingContent: @Composable () -> Unit,
    currentlyLoading: Boolean = false,
    finishedLoading: Boolean = false,
    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState()

    Column(modifier = modifier.wrapContentHeight(Alignment.Top)) {

        if (items.isEmpty()) emptyContent.invoke()
        else {
            LazyColumn(state = listState, modifier = Modifier.weight(1f)) {

                itemsIndexed(items = items, itemContent = { index, item ->

                    AnimatedListItem(
                        state = listState,
                        index = index,
                        item = item,
                        itemContent = { _, modifier ->
                            item?.let { data ->
                                itemContent.invoke(data, modifier)
                            } ?: if (!finishedLoading) loadingContent.invoke()
                        }
                    )

                })

            } // LazyColumn

            if (listState.isScrolledToTheEnd() && !finishedLoading && !currentlyLoading) {
                onLoadMore.invoke()
            }

        } // else

    } // Column
}