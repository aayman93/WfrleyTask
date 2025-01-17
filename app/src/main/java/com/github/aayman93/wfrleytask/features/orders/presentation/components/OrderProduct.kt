package com.github.aayman93.wfrleytask.features.orders.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetail
import com.github.aayman93.wfrleytask.features.orders.domain.models.Product
import com.github.aayman93.wfrleytask.ui.theme.Neutral500
import com.github.aayman93.wfrleytask.ui.theme.Neutral700
import com.github.aayman93.wfrleytask.ui.theme.Primary700
import com.github.aayman93.wfrleytask.ui.theme.Text12Medium
import com.github.aayman93.wfrleytask.ui.theme.Text16SemiBold
import com.github.aayman93.wfrleytask.utils.formatDouble

@Composable
fun OrderProduct(
    orderDetail: OrderDetail,
    modifier: Modifier = Modifier
) {

    val formatedQuantity = remember(orderDetail) {
        orderDetail.quantity.formatDouble()
    }

    val formatedProductPrice = remember(orderDetail) {
        orderDetail.product.price.formatDouble()
    }

    val formatedItemPrice = remember(orderDetail) {
        orderDetail.rowPriceAfterDiscount.formatDouble(alwaysHasDecimal = true)
    }

    Row(
        modifier = modifier
            .height(64.dp)
            .clip(RoundedCornerShape(6.dp)),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFFD9D9D9))
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(end = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = orderDetail.product.name,
                style = Text16SemiBold,
                color = Neutral700,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(
                    id = R.string.product_quantity_price,
                    formatedQuantity,
                    formatedProductPrice
                ),
                style = Text12Medium,
                color = Neutral500,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = formatedItemPrice,
                style = Text16SemiBold,
                color = Primary700,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(locale = "ar", showBackground = true)
@Composable
private fun OrderProductPreview() {
    OrderProduct(
        orderDetail = OrderDetail(
            id = 1,
            quantity = 3.0,
            rowPriceAfterDiscount = 50.0,
            rowTotal = 62.5,
            product = Product(
                name = "توست ريتش بيك",
                price = 35.0
            )
        )
    )
}