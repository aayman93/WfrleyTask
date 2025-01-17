package com.github.aayman93.wfrleytask.utils

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun AutoResizedText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    val textColor = color.takeOrElse { style.color.takeOrElse { LocalContentColor.current } }
    val defaultFontSize = MaterialTheme.typography.bodyMedium.fontSize
    var resizedFontSize by remember {
        mutableStateOf(
            if (style.fontSize == TextUnit.Unspecified) defaultFontSize else style.fontSize
        )
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }

    Text(
        text = text,
        modifier = modifier.drawWithContent {
            if (shouldDraw) {
                drawContent()
            }
        },
        softWrap = false,
        style = style.copy(fontSize = resizedFontSize),
        color = textColor,
        textAlign = textAlign,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                resizedFontSize *= 0.95
            } else {
                shouldDraw = true
            }
        }
    )
}