package com.newstore.favqs.features.account.signup.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Login
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.newstore.compose.button.PrimaryButton
import com.newstore.compose.button.SecondaryButton
import com.newstore.compose.input.InputField
import com.newstore.compose.input.PasswordInputField
import com.newstore.compose.keyboard.KeyboardStyle
import com.newstore.compose.layout.CenteredColumn
import com.newstore.compose.layout.DefaultSpacer
import com.newstore.compose.layout.LargeSpacer
import com.newstore.compose.scroll.enableVerticalScroll
import com.newstore.compose.text.TitleText
import com.newstore.favqs.features.account.AccountTexts
import com.newstore.favqs.features.main.util.LocalKeyboardManager

@Composable
fun SignupScreen(
    onUserNameEntered: (String) -> Unit,
    usernameError: String? = null,
    onEmailEntered: (String) -> Unit,
    emailError: String? = null,
    onPasswordEntered: (String) -> Unit,
    passwordError: String? = null,
    onPasswordAgainEntered: (String) -> Unit,
    passwordAgainError: String? = null,
    onSignupRequest: () -> Unit,
    onBackNavigation: () -> Unit
) {

    val keyboardManager = LocalKeyboardManager.current

    CenteredColumn(modifier = Modifier.enableVerticalScroll()) {

        TitleText(text = SignUpTexts.PAGE_TITLE)

        LargeSpacer()

        InputField(
            label = AccountTexts.USERNAME,
            onTextChanged = { onUserNameEntered.invoke(it) },
            errorMessage = usernameError
        )

        DefaultSpacer()

        InputField(
            label = AccountTexts.EMAIL,
            onTextChanged = { onEmailEntered.invoke(it) },
            errorMessage = emailError
        )

        DefaultSpacer()

        PasswordInputField(
            label = AccountTexts.PASSWORD,
            onTextChanged = { onPasswordEntered.invoke(it) },
            errorMessage = passwordError,
            keyboardStyle = KeyboardStyle(keyboardType = KeyboardType.Password)
        )

        DefaultSpacer()

        PasswordInputField(
            label = AccountTexts.PASSWORD_AGAIN,
            onTextChanged = { onPasswordAgainEntered.invoke(it) },
            errorMessage = passwordAgainError,
            keyboardStyle = KeyboardStyle(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Go,
                keyboardAction = {
                    keyboardManager.hide()
                    onSignupRequest()
                }
            )
        )

        LargeSpacer()

        PrimaryButton(
            label = SignUpTexts.PAGE_TITLE,
            icon = Icons.Filled.AppRegistration,
            onClick = {
                keyboardManager.hide()
                onSignupRequest()
            }
        )

        DefaultSpacer()

        SecondaryButton(
            label = SignUpTexts.NAVIGATE_TO_LOGIN,
            icon = Icons.Filled.Login,
            onClick = { onBackNavigation() }
        )
    }

}