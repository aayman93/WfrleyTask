package com.github.aayman93.wfrleytask.features.orders.presentation.order_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.ui.theme.Neutral200
import com.github.aayman93.wfrleytask.ui.theme.Neutral500
import com.github.aayman93.wfrleytask.ui.theme.Neutral700
import com.github.aayman93.wfrleytask.ui.theme.Text12Medium
import com.github.aayman93.wfrleytask.ui.theme.Text16SemiBold

@Composable
fun OrderInfo(
    customerName: String,
    phone: String,
    address: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(vertical = 16.dp, horizontal = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.order_info),
            style = Text16SemiBold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = Neutral200
        )
        Spacer(Modifier.height(16.dp))
        OrderInfoItem(
            title = stringResource(R.string.customer_name),
            value = customerName
        )
        Spacer(Modifier.height(8.dp))
        OrderInfoItem(
            title = stringResource(R.string.phone),
            value = phone
        )
        Spacer(Modifier.height(8.dp))
        OrderInfoItem(
            title = stringResource(R.string.address),
            value = address
        )
    }
}

@Composable
private fun OrderInfoItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = Text12Medium,
            color = Neutral500,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = value,
            style = Text12Medium,
            color = Neutral700,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(locale = "ar")
@Composable
private fun OrderInfoPreview() {
    OrderInfo(
        modifier = Modifier.fillMaxWidth(),
        customerName = "احمد",
        phone = "0123456789",
        address = "المنصورة"
    )
}