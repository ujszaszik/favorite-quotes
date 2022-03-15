package com.newstore.compose.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.newstore.compose.keyboard.KeyboardStyle
import com.newstore.compose.resources.Colors
import com.newstore.compose.resources.Dimens
import com.newstore.compose.resources.Icons
import com.newstore.extension.empty

@Composable
fun SearchView(onSearchRequest: (String) -> Unit) {

    var searchText by remember { mutableStateOf(String.empty) }

    val keyboardStyle = KeyboardStyle(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Search,
        keyboardAction = { onSearchRequest(searchText) }
    )

    TextField(
        value = searchText,
        onValueChange = { value ->
            searchText = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = Colors.titleBlue,
            fontSize = Dimens.highlightTextSize
        ),
        leadingIcon = { Icons.SearchViewLeadingIcon() },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(
                    onClick = { searchText = String.empty }
                ) { Icons.SearchViewCloseIcon() }
            }
        },
        keyboardOptions = keyboardStyle.keyboardOptions,
        keyboardActions = keyboardStyle.keyboardActions,
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Colors.titleBlue,
            cursorColor = Colors.titleBlue,
            leadingIconColor = Colors.titleBlue,
            trailingIconColor = Colors.steelGray,
            backgroundColor = Colors.white,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}