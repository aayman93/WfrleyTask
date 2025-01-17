package com.github.aayman93.wfrleytask.features.orders.presentation.new_order.create_order.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.features.orders.presentation.components.OrdersTextField
import com.github.aayman93.wfrleytask.ui.theme.Neutral500

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OrdersTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                contentDescription = null,
                tint = Neutral500
            )
        }
    )
}

@Preview(locale = "ar", showBackground = true)
@Composable
private fun SearchBarPreview() {
    SearchBar(
        value = "",
        onValueChange = {},
        placeholder = "بحث بالإسم او الباركود"
    )
}