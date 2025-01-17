package com.github.aayman93.wfrleytask.features.orders.presentation.new_order.create_order.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.ui.theme.Primary700
import com.github.aayman93.wfrleytask.ui.theme.Text12SemiBold
import com.github.aayman93.wfrleytask.ui.theme.Text16Medium
import com.github.aayman93.wfrleytask.ui.theme.Text16SemiBold
import com.github.aayman93.wfrleytask.utils.AutoResizedText
import com.github.aayman93.wfrleytask.utils.formatDouble

@Composable
fun CartSummary(
    quantity: Double,
    price: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val totalQuantity = remember(quantity) {
        quantity.formatDouble()
    }
    val totalPrice = remember(price) {
        price.formatDouble(alwaysHasDecimal = true)
    }

    Row(
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Primary700)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.finish_order),
                style = Text16Medium,
                color = Color.White,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                AutoResizedText(
                    text = totalQuantity,
                    style = Text12SemiBold,
                    color = Primary700,
                    textAlign = TextAlign.Center
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(R.string.price_with_short_unit, totalPrice),
                style = Text16SemiBold,
                color = Color.White,
                textAlign = TextAlign.End
            )
            Spacer(Modifier.width(16.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_next),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview(locale = "ar")
@Composable
private fun CartSummaryPreview() {
    CartSummary(
        modifier = Modifier.fillMaxWidth(),
        quantity = 3.0,
        price = 10.0,
        onClick = {}
    )
}