package com.newstore.compose.input

import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.newstore.compose.keyboard.KeyboardStyle
import com.newstore.compose.resources.Icons

@Composable
fun PasswordInputField(
    label: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String? = null,
    keyboardStyle: KeyboardStyle = KeyboardStyle.DEFAULT
) {

    var passwordVisible by remember { mutableStateOf(false) }

    InputField(
        label = label,
        onTextChanged = { onTextChanged(it) },
        errorMessage = errorMessage,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardStyle = keyboardStyle,
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                if (passwordVisible) Icons.PasswordVisibilityOffIcon()
                else Icons.PasswordVisibilityOnIcon()
            }
        }
    )
}