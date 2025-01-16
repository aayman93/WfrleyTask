package com.github.aayman93.wfrleytask.features.orders.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.aayman93.wfrleytask.R
import com.github.aayman93.wfrleytask.features.orders.domain.models.Order
import com.github.aayman93.wfrleytask.features.orders.presentation.home.components.CreateOrder
import com.github.aayman93.wfrleytask.features.orders.presentation.home.components.HomeScreenTopBar
import com.github.aayman93.wfrleytask.features.orders.presentation.home.components.OrderItem
import com.github.aayman93.wfrleytask.features.orders.presentation.home.components.OrdersEmptyState
import com.github.aayman93.wfrleytask.ui.theme.Neutral200
import com.github.aayman93.wfrleytask.ui.theme.Neutral700
import com.github.aayman93.wfrleytask.ui.theme.Primary700
import com.github.aayman93.wfrleytask.utils.showToast
import kotlinx.coroutines.flow.flow

@Composable
fun HomeScreen(
    onCreateOrderClick: () -> Unit,
    onOrderClick: (Int) -> Unit
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val orders = viewModel.orders.collectAsLazyPagingItems()

    val context = LocalContext.current

    LaunchedEffect(orders.loadState.refresh) {
        if (orders.loadState.refresh is LoadState.Error) {
            showToast(
                context = context,
                message = (orders.loadState.refresh as LoadState.Error).error.message.orEmpty()
            )
        }
    }

    HomeScreenContent(
        orders = orders,
        onCreateOrderClick = onCreateOrderClick,
        onOrderClick = onOrderClick
    )
}

@Composable
private fun HomeScreenContent(
    orders: LazyPagingItems<Order>,
    onCreateOrderClick: () -> Unit,
    onOrderClick: (Int) -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Scaffold(
        topBar = {
            HomeScreenTopBar(
                storeName = stringResource(R.string.local_store_name),
                storeImage = R.drawable.store_image,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF7DDDE1),
                                Primary700
                            )
                        )
                    )
                    .statusBarsPadding()
            )
        }
    ) { innerPadding ->
        if (orders.loadState.refresh is LoadState.Loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Primary700)
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(state = rememberScrollState())
            ) {
                Spacer(Modifier.height(24.dp))

                CreateOrder(
                    onClick = onCreateOrderClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )

                Spacer(Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.all_orders),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Neutral700,
                    textAlign = TextAlign.Start
                )

                Spacer(Modifier.height(16.dp))

                if (orders.itemCount == 0) {
                    OrdersEmptyState(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                            .heightIn(max = screenHeight),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(
                            count = orders.itemCount,
                            key = { index -> orders[index]?.id ?: index },
                            contentType = { index -> orders[index] },
                            itemContent = { index ->
                                orders[index]?.let { order ->
                                    OrderItem(
                                        customerName = order.customerName,
                                        orderDate = order.orderDate,
                                        price = order.price,
                                        onOrderClick = {
                                            onOrderClick(order.id)
                                        }
                                    )
                                    if (index != orders.itemCount - 1) {
                                        HorizontalDivider(
                                            thickness = 1.dp,
                                            color = Neutral200
                                        )
                                    }
                                }
                            }
                        )
                    }
                }

            }
        }
    }
}

@Preview(locale = "ar", showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        orders = flow<PagingData<Order>> { }.collectAsLazyPagingItems(),
        onCreateOrderClick = {},
        onOrderClick = {}
    )
}