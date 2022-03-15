package com.newstore.favqs.features.account.login.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.newstore.compose.button.PrimaryButton
import com.newstore.compose.input.InputField
import com.newstore.compose.input.PasswordInputField
import com.newstore.compose.keyboard.KeyboardStyle
import com.newstore.compose.layout.CenteredColumn
import com.newstore.compose.layout.DefaultSpacer
import com.newstore.compose.layout.LargeSpacer
import com.newstore.compose.scroll.enableVerticalScroll
import com.newstore.compose.text.DescriptionText
import com.newstore.compose.text.TitleText
import com.newstore.favqs.features.account.AccountTexts
import com.newstore.favqs.features.account.signup.ui.SignupHost
import com.newstore.favqs.features.main.util.LocalKeyboardManager
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.navigate

@Composable
fun LoginScreen(
    onUserNameEntered: (String) -> Unit,
    usernameError: String? = null,
    onPasswordEntered: (String) -> Unit,
    passwordError: String? = null,
    onLoginRequest: () -> Unit
) {

    val navController = LocalNavController.current
    val keyboardManager = LocalKeyboardManager.current

    CenteredColumn(modifier = Modifier.enableVerticalScroll()) {

        TitleText(text = LoginTexts.PAGE_TITLE)

        LargeSpacer()

        InputField(
            label = AccountTexts.USERNAME,
            onTextChanged = { onUserNameEntered.invoke(it) },
            errorMessage = usernameError
        )

        DefaultSpacer()

        PasswordInputField(
            label = AccountTexts.PASSWORD,
            onTextChanged = { onPasswordEntered.invoke(it) },
            errorMessage = passwordError,
            keyboardStyle = KeyboardStyle(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Go,
                keyboardAction = {
                    keyboardManager.hide()
                    onLoginRequest()
                }
            )
        )

        LargeSpacer()

        PrimaryButton(
            label = LoginTexts.PAGE_TITLE,
            icon = Icons.Filled.Login,
            onClick = {
                keyboardManager.hide()
                onLoginRequest()
            }
        )

        LargeSpacer()

        DescriptionText(
            text = LoginTexts.signupLinkText,
            linkTags = arrayOf(LoginTexts.SIGNUP_TEXT_LINK_TAG),
            doOnLinkClicked = { navController.navigate(SignupHost) },
        )
    }

}