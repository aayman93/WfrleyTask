package com.github.aayman93.wfrleytask.features.orders.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.ui.theme.Neutral500
import com.github.aayman93.wfrleytask.ui.theme.Primary100
import com.github.aayman93.wfrleytask.ui.theme.Primary700
import com.github.aayman93.wfrleytask.ui.theme.Text10Regular
import com.github.aayman93.wfrleytask.ui.theme.Text12Medium
import com.github.aayman93.wfrleytask.ui.theme.Text16Medium
import com.github.aayman93.wfrleytask.utils.formatDouble
import com.github.aayman93.wfrleytask.utils.toDateTime

@Composable
fun OrderItem(
    customerName: String,
    orderDate: String,
    price: Double,
    onOrderClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formatedOrderDate = remember(orderDate) {
        orderDate.toDateTime()
    }
    
    val formatedPrice = remember(price) {
        price.formatDouble()
    }

    Row(
        modifier = modifier
            .clickable(onClick = onOrderClick)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Primary100),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_cart),
                contentDescription = null,
                tint = Primary700,
                modifier = Modifier.size(24.dp)
            )
        }

        Column(
            modifier = Modifier
                .height(48.dp)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = formatedOrderDate.ifEmpty {
                        stringResource(R.string.fall_back)
                    },
                    style = Text16Medium,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = stringResource(R.string.price_with_short_unit, formatedPrice),
                    style = Text12Medium,
                    color = Primary700,
                    textAlign = TextAlign.End
                )
            }
            Text(
                text = customerName.ifEmpty {
                    stringResource(R.string.fall_back)
                },
                style = Text10Regular,
                color = Neutral500,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(locale = "ar", showBackground = true)
@Composable
private fun OrderItemPreview() {
    OrderItem(
        customerName = "احمد",
        orderDate = "2025-01-14T12:38:06.3058988",
        price = 300.0,
        onOrderClick = {}
    )
}