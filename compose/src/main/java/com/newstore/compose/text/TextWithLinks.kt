package com.newstore.compose.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import com.newstore.extension.empty

@Composable
fun TextWithLinks(
    text: AnnotatedString,
    textStyle: TextStyle,
    onLinkClicked: (String) -> Unit,
    testTag: String = String.empty,
    vararg linkTags: String
) {
    ClickableText(
        modifier = Modifier.testTag(testTag),
        text = text,
        style = textStyle,
        onClick = { offset ->
            linkTags.forEach { tag ->
                text.getStringAnnotations(
                    tag = tag,
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    onLinkClicked.invoke(it.item)
                }
            }
        }
    )
}