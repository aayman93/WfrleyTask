package com.github.aayman93.wfrleytask.features.orders.presentation.order_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.ui.theme.Neutral500
import com.github.aayman93.wfrleytask.ui.theme.Primary100
import com.github.aayman93.wfrleytask.ui.theme.Primary700
import com.github.aayman93.wfrleytask.ui.theme.Text14Regular
import com.github.aayman93.wfrleytask.ui.theme.Text14SemiBold
import com.github.aayman93.wfrleytask.ui.theme.Text16SemiBold
import com.github.aayman93.wfrleytask.utils.formatDouble

@Composable
fun OrderSummary(
    paymentMethod: String,
    totalDiscount: Double,
    totalPrice: Double,
    modifier: Modifier = Modifier
) {
    val formatedTotalDiscount = remember(totalDiscount) {
        totalDiscount.formatDouble()
    }

    val formatedTotalPrice = remember(totalPrice) {
        totalPrice.formatDouble()
    }

    Column(
        modifier = modifier
            .background(Primary100)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.processes),
            style = Text16SemiBold,
            color = Primary700,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )

        OrderSummaryItem(
            title = stringResource(R.string.payment_method),
            value = paymentMethod
        )

        OrderSummaryItem(
            title = stringResource(R.string.total_discount),
            value = stringResource(R.string.price_with_full_unit, formatedTotalDiscount)
        )

        OrderSummaryItem(
            title = stringResource(R.string.total_price),
            value = stringResource(R.string.price_with_unit, formatedTotalPrice),
            textStyle = Text14SemiBold,
            textColor = Primary700
        )
    }
}

@Composable
private fun OrderSummaryItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = Text14Regular,
    textColor: Color = Neutral500
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = textStyle,
            color = textColor,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = value,
            style = textStyle,
            color = textColor,
            textAlign = TextAlign.End,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(locale = "ar")
@Composable
private fun OrderSummaryPreview() {
    OrderSummary(
        paymentMethod = "نقدي",
        totalDiscount = 148.0,
        totalPrice = 394.93
    )
}