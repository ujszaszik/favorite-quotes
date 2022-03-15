package com.newstore.compose.resources

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Dimens {

    // PADDING
    val paddingLarge = 32.dp
    val paddingDouble = 16.dp
    val paddingDefault = 8.dp
    val paddingHalf = 4.dp

    private val paddingDefaultHorizontal = 20.dp
    private val paddingDefaultVertical = 12.dp
    val paddingDefaultValues = PaddingValues(
        start = paddingDefaultHorizontal,
        top = paddingDefaultVertical,
        end = paddingDefaultHorizontal,
        bottom = paddingDefaultVertical
    )

    // CARD
    val cardCornerRadius = 8.dp
    val cardElevation = 6.dp

    // BUTTON
    val buttonCornerRadius = 24.dp

    // SEPARATOR
    val defaultSeparatorHeight = 1.dp

    // TEXT
    val smallTextSize = 13.sp
    val defaultTextSize = 15.sp
    val highlightTextSize = 16.sp
    val detailsAuthorTextSize = 18.sp
    val detailsQuoteTextSize = 20.sp
    val counterTextSize = 20.sp
    val appBarTextSize = 30.sp
    val titleTextSize = 50.sp


    // ICON
    val defaultIconSize = 20.dp
    val largerIconSize = 24.dp
    val emptyListIconSize = 240.dp

    // LOGO
    val logoWidth = 160.dp
    val logoHeight = 60.dp

}