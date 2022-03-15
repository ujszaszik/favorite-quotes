package com.newstore.compose.resources

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.newstore.compose.R
import com.newstore.extension.empty

object Images {

    @Composable
    fun NewStoreLogo() {
        Image(
            painter = painterResource(R.drawable.from_new_store),
            modifier = Modifier
                .width(Dimens.logoWidth)
                .height(Dimens.logoHeight),
            contentDescription = String.empty
        )
    }
}