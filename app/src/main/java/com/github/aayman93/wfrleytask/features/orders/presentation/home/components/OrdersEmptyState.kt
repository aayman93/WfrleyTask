package com.github.aayman93.wfrleytask.features.orders.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.ui.theme.Neutral700
import com.github.aayman93.wfrleytask.ui.theme.Text16Bold

@Composable
fun OrdersEmptyState(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_empty_cart),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(160.dp)
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.order_empty_state_title),
            style = Text16Bold,
            color = Neutral700,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OrdersEmptyStatePreview() {
    OrdersEmptyState(
        modifier = Modifier.fillMaxSize()
    )
}