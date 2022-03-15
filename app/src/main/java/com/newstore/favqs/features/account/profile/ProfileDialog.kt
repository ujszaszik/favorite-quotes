package com.newstore.favqs.features.account.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hail
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.newstore.compose.dialog.QuoteAlertDialog
import com.newstore.compose.layout.CenteredColumn
import com.newstore.compose.layout.LoadingBox
import com.newstore.compose.layout.ProgressType
import com.newstore.compose.text.GreetingText

@Composable
fun ProfileDialog(
    isLoading: Boolean,
    username: String?,
    onClosed: () -> Unit
) {

    LoadingBox(type = ProgressType.ABOVE_SCREEN, isLoading = isLoading) {

        CenteredColumn(modifier = Modifier.fillMaxWidth()) {

            var showDialog by remember { mutableStateOf(true) }

            if (showDialog) {
                QuoteAlertDialog(
                    imageVector = Icons.Default.Hail,
                    body = { GreetingText(username) },
                    onOkay = {
                        showDialog = false
                        onClosed()
                    },
                )
            }
        } //CenteredColumn

    } //LoadingBox
}