package com.newstore.favqs.features.account.logout.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.newstore.compose.dialog.QuoteAlertDialog
import com.newstore.compose.layout.CenteredColumn

@Composable
fun LogoutDialog(
    onLogoutApproved: () -> Unit,
    onLogoutCancelled: () -> Unit
) {
    CenteredColumn(modifier = Modifier.fillMaxWidth()) {

        var showDialog by remember { mutableStateOf(true) }

        if (showDialog) {
            QuoteAlertDialog(
                title = LogoutTexts.DIALOG_TITLE,
                description = LogoutTexts.DIALOG_DESCRIPTION,
                onOkay = {
                    showDialog = false
                    onLogoutApproved()
                },
                onCancel = {
                    showDialog = false
                    onLogoutCancelled()
                }
            ) // QuoteAlertDialog
        }

    } // CenteredColumn
}