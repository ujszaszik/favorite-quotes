package com.newstore.compose.layout

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.newstore.compose.resources.Dimens

@Composable
fun DefaultSpacer() {
    Spacer(modifier = Modifier.height(Dimens.paddingDefault))
}

@Composable
fun DoubleSpacer() {
    Spacer(modifier = Modifier.height(Dimens.paddingDouble))
}

@Composable
fun LargeSpacer() {
    Spacer(modifier = Modifier.height(Dimens.paddingLarge))
}