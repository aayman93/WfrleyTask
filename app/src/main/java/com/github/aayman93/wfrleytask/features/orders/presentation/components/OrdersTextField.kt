package com.github.aayman93.wfrleytask.features.orders.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.github.aayman93.wfrleytask.ui.theme.Neutral300
import com.github.aayman93.wfrleytask.ui.theme.Neutral500
import com.github.aayman93.wfrleytask.ui.theme.Neutral700
import com.github.aayman93.wfrleytask.ui.theme.Primary700
import com.github.aayman93.wfrleytask.ui.theme.Text12Regular
import com.github.aayman93.wfrleytask.ui.theme.Text16Regular
import com.github.aayman93.wfrleytask.utils.applyIf

@Composable
fun OrdersTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var isFocused by remember { mutableStateOf(false) }

    val density = LocalDensity.current

    Box(
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(6.dp))
            .border(
                width = 1.dp,
                color = if (isFocused) {
                    Primary700
                } else {
                    Neutral300
                },
                shape = RoundedCornerShape(6.dp)
            )
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = if (value.isEmpty() && !isFocused) {
                {
                    Text(
                        text = placeholder,
                        style = Text12Regular,
                        color = Neutral500
                    )
                }
            } else null,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .applyIf(isFocused) {
                    offset {
                        return@offset IntOffset(
                            x = 0,
                            y = with(density) {
                                9.dp.roundToPx()
                            }
                        )
                    }
                },
            textStyle = Text16Regular,
            leadingIcon = leadingIcon,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Neutral700,
                unfocusedTextColor = Neutral700,
                cursorColor = Primary700
            ),
            shape = RoundedCornerShape(6.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
        if (isFocused) {
            Text(
                text = placeholder,
                style = Text12Regular,
                color = Neutral500,
                modifier = Modifier.padding(top = 9.dp, start = 16.dp)
                    .align(Alignment.TopStart)
            )
        }
    }
}

@Preview(locale = "ar", showBackground = true)
@Composable
private fun OrdersTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    OrdersTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
        },
        placeholder = "اسم العميل"
    )
}