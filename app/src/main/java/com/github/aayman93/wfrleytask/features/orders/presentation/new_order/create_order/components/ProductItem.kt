package com.github.aayman93.wfrleytask.features.orders.presentation.new_order.create_order.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.github.aayman93.wfrleytask.features.orders.domain.models.Product
import com.github.aayman93.wfrleytask.ui.theme.Neutral300
import com.github.aayman93.wfrleytask.ui.theme.Neutral50
import com.github.aayman93.wfrleytask.ui.theme.Neutral500
import com.github.aayman93.wfrleytask.ui.theme.Neutral700
import com.github.aayman93.wfrleytask.ui.theme.Primary100
import com.github.aayman93.wfrleytask.ui.theme.Primary200
import com.github.aayman93.wfrleytask.ui.theme.Primary700
import com.github.aayman93.wfrleytask.ui.theme.Text12Medium
import com.github.aayman93.wfrleytask.ui.theme.Text16Medium
import com.github.aayman93.wfrleytask.ui.theme.Text20Medium
import com.github.aayman93.wfrleytask.utils.formatDouble

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    quantityInOrder: Double = 0.0,
    onAddToOrder: () -> Unit = {},
    onRemoveFromOrder: () -> Unit = {}
) {
    val isSelected = remember(quantityInOrder) {
        quantityInOrder > 0
    }

    val productPrice = remember(product.price) {
        product.price.formatDouble()
    }

    val productQuantity = remember(product.saleableQuantity) {
        product.saleableQuantity.formatDouble()
    }

    val orderQuantity = remember(quantityInOrder) {
        quantityInOrder.formatDouble()
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = if (isSelected) Primary700 else Neutral300,
                shape = RoundedCornerShape(8.dp)
            )
            .background(color = if (isSelected) Primary100 else Color.White)
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
            )
            TextWithBackground(
                value = stringResource(R.string.price_with_unit, productPrice)
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = product.name,
            style = Text16Medium,
            color = Neutral700,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        TextWithBackground(
            value = stringResource(R.string.product_quantity, productQuantity),
            backgroundColor = Neutral50,
            textColor = Neutral500
        )
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Primary700)
                        .clickable(onClick = onAddToOrder),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_plus),
                        contentDescription = stringResource(R.string.add_to_order),
                        tint = Color.White
                    )
                }
                Text(
                    text = orderQuantity,
                    style = Text20Medium,
                    color = Neutral700,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Primary700)
                        .clickable(onClick = onRemoveFromOrder),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_minus),
                        contentDescription = stringResource(R.string.remove_from_order),
                        tint = Color.White
                    )
                }
            } else {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp),
                    shape = RoundedCornerShape(6.dp),
                    onClick = onAddToOrder,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary700,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(R.string.add_to_order),
                        style = Text12Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun TextWithBackground(
    value: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Primary200,
    textColor: Color = Primary700
) {
    Box(
        modifier = modifier
            .height(23.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(color = backgroundColor)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            style = Text12Medium,
            color = textColor
        )
    }
}

@Preview(locale = "ar")
@Composable
private fun ProductItemPreview() {
    ProductItem(
        modifier = Modifier.size(width = 156.dp, height = 171.dp),
        quantityInOrder = 2.0,
        product = Product(
            name = "سبيرو سباتس",
            price = 15.0,
            saleableQuantity = 15.0
        )
    )
}