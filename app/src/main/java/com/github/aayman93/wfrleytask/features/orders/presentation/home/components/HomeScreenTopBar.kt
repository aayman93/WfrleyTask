package com.github.aayman93.wfrleytask.features.orders.presentation.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.ui.theme.Text16Medium
import com.github.aayman93.wfrleytask.ui.theme.Text20Bold
import com.github.aayman93.wfrleytask.utils.getTodayDateFormatted

@Composable
fun HomeScreenTopBar(
    storeName: String,
    @DrawableRes storeImage: Int,
    modifier: Modifier = Modifier
) {
    val date = remember(Unit) {
        getTodayDateFormatted()
    }

    Row(
        modifier = modifier
            .padding(vertical = 24.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            painter = painterResource(storeImage),
            contentDescription = storeName,
            modifier = Modifier.size(48.dp)
        )
        Column(
            modifier = Modifier
                .height(48.dp)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = storeName,
                style = Text20Bold,
                color = Color.White,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = date,
                style = Text16Medium,
                color = Color.White,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(locale = "ar")
@Composable
private fun HomeScreenTopBarPreview() {
    HomeScreenTopBar(
        storeName = stringResource(R.string.local_store_name),
        storeImage = R.drawable.store_image,
    )
}