package com.newstore.favqs.features.quote.details.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import com.newstore.compose.button.Counter
import com.newstore.compose.resources.Colors

@Composable
fun UpVoteCounter(value: Number, onClick: () -> Unit) {
    Counter(
        value = value,
        textColor = Colors.green,
        icon = Icons.Default.ArrowUpward,
        iconTint = Colors.green,
        onClick = onClick
    )
}

@Composable
fun DownVoteCounter(value: Number, onClick: () -> Unit) {
    Counter(
        value = value,
        textColor = Colors.red,
        icon = Icons.Default.ArrowDownward,
        iconTint = Colors.red,
        onClick = onClick
    )
}

@Composable
fun FavoriteCounter(value: Number, onClick: () -> Unit) {
    Counter(
        value = value,
        textColor = Colors.yellow,
        icon = Icons.Default.Star,
        iconTint = Colors.yellow,
        onClick = onClick
    )
}