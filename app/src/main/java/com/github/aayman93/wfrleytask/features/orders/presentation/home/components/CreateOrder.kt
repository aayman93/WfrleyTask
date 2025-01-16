package com.github.aayman93.wfrleytask.features.orders.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.ui.theme.Primary50
import com.github.aayman93.wfrleytask.ui.theme.Primary700

@Composable
fun CreateOrder(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = Primary700, shape = RoundedCornerShape(8.dp))
            .background(Primary50)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_cart),
            contentDescription = null,
            tint = Color.Unspecified
        )

        Text(
            text = stringResource(R.string.add_new_order),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Primary700,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(locale = "ar")
@Composable
private fun CreateOrderPreview() {
    CreateOrder(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    )
}