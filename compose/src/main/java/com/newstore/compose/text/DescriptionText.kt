package com.newstore.compose.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.newstore.compose.resources.Dimens
import com.newstore.extension.empty

@Composable
fun DescriptionText(
    modifier: Modifier = Modifier,
    text: CharSequence,
    textAlign: TextAlign = TextAlign.Center,
    doOnLinkClicked: (String) -> Unit = {},
    testTag: String = String.empty,
    vararg linkTags: String,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val textStyle = TextStyle(
            fontSize = Dimens.smallTextSize,
            textAlign = textAlign
        )

        when (text) {
            is AnnotatedString -> {
                TextWithLinks(
                    text = text,
                    textStyle = textStyle,
                    onLinkClicked = { doOnLinkClicked.invoke(it) },
                    linkTags = linkTags,
                    testTag = testTag
                )
            }
            else -> {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .testTag(testTag),
                    text = text.toString(),
                    style = textStyle
                )
            }
        }
    }
}