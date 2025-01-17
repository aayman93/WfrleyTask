package com.github.aayman93.wfrleytask.features.orders.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.ui.theme.Text16Bold

@Composable
fun OrdersTopBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = Color.White,
    hasHomeAction: Boolean = true,
    onHomeClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .padding(bottom = 20.dp, start = 16.dp, end = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                    contentDescription = stringResource(R.string.back),
                    tint = contentColor
                )
            }

            Text(
                text = title,
                style = Text16Bold,
                color = contentColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 8.dp)
            )

            IconButton(
                modifier = Modifier.size(24.dp)
                    .alpha(if (hasHomeAction) 1f else 0f),
                onClick = onHomeClick,
                enabled = hasHomeAction
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_home),
                    contentDescription = stringResource(R.string.home),
                    tint = contentColor
                )
            }
        }
    }
}

@Preview(locale = "ar")
@Composable
private fun OrdersTopBarPreview() {
    OrdersTopBar(
        title = "Orders",
        hasHomeAction = true,
        onBackClick = {}
    )
}